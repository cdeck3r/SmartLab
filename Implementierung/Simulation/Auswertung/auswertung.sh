#!/bin/bash
#
#istList=$1
#istList=/mnt/c/Users/Smi/Desktop/SMARTLAB_Simulator/Auswertung/istlist.csv
#sollList=$2
#sollList=/mnt/c/Users/Smi/Desktop/SMARTLAB_Simulator/Auswertung/solllist.csv

historie_anzahlZuviele=()
historie_anzahlFehlende=()
historie_einkaufstage=()

if [ $# != 2 ]
then
	echo "Verwendung: ./auswertung.sh <Ist-Liste.csv> <Soll-Liste.csv>"
	exit 1
fi

istList=$1
istList_Bezeichnung=`basename $istList | sed 's/.csv$//g' | sed 's/istEinkaufsliste_//g'`
sollList=$2

outputPath=./meta_files/auswertung_$istList_Bezeichnung.dat
outputPathCSV=./output_files/auswertung_$istList_Bezeichnung.csv

while read lineSoll
do
	if [ `echo $lineSoll | grep ^[0-9]` ]
	then	
		shoppingDay=$(echo $lineSoll | cut -d";" -f1)
		lineIst=$(cat $istList | grep "^$shoppingDay;")
		
		if [ -z "$lineIst" ]
		then
			echo "No Shoppinglist available for day $shoppingDay" >> /dev/stderr
			continue
		fi

		# Metriken bestimmen
		anzahlZuviele=0
		for productIst in `echo "$lineIst" | sed 's/^[0-9]*;//g' | sed 's/;$//g' | tr ";" "\n"`
		do
			needed=0
			for productSoll in `echo "$lineSoll" | sed 's/^[0-9]*;//g' | sed 's/;$//g' | tr ";" "\n"`
			do
				if [ "$productIst" == "$productSoll" ]
				then
					needed=1
				fi
			done
			if [ $needed == 0 ]
			then
				anzahlZuviele=$((anzahlZuviele + 1))
			fi

		done
		
		historie_anzahlZuviele+=($anzahlZuviele)
		
		anzahlFehlende=0
		for productSoll in `echo "$lineSoll" | sed 's/^[0-9]*;//g' | sed 's/;$//g' | tr ";" "\n" `
		do
			inList=0
			for productIst in `echo "$lineIst" | sed 's/^[0-9]*;//g' | sed 's/;$//g' | tr ";" "\n"`
			do
				if [ "$productSoll" == "$productIst" ]
				then
					inList=1
				fi
			done
			if [ $inList == 0 ]
			then
				anzahlFehlende=$((anzahlFehlende + 1))
			fi
		done

		historie_anzahlFehlende+=($anzahlFehlende)

		einkaufstag=`echo $lineSoll | cut -d";" -f1`
		historie_Einkaufstage+=($einkaufstag)

		anzahlSoll=`echo "$lineSoll" | sed 's/^[0-9]*;//g' | sed 's/;$//g' | tr ";" "\n" | wc -l`
		anzahlIst=`echo "$lineIst" | sed 's/^[0-9]*;//g' | sed 's/;$//g' | tr ";" "\n" | wc -l`
		
		# --- Ausgaben ---
		#echo ""
		#echo "--- Day $shoppingDay ---"
		#echo "Soll: $(echo "$lineSoll" | sed 's/^[0-9]*;//g' | sed 's/;$//g')"
		#echo "Ist:  $(echo "$lineIst" | sed 's/^[0-9]*;//g' | sed 's/;$//g')"
		#echo "-"
		#echo "anzahlZuviele: $anzahlZuviele"
		#echo "anzahlFehlende: $anzahlFehlende"
		#echo "-"
		#echo "Quote: Anzahl 'zuviele' / Anzahl Produkte der Einkaufsliste = $anzahlZuviele / $anzahlIst"

		#echo "Quote: Anzahl 'fehlende' / Anzahl der Sollliste = $anzahlFehlende / $anzahlSoll"
		#echo "aktuelle Historie Fehlende:"
		#echo "${historie_anzahlFehlende[*]}"
		#echo ""
		#echo "aktuelle Historie Zuviele:"
		#echo "${historie_anzahlZuviele[*]}"

	fi
done < $sollList

#echo " ######### ENDERGEBNIS: ##########"
#echo "Historie Fehlende:"
#echo "${historie_anzahlFehlende[*]}"
#echo ""
#echo "Historie Zuviele:"
#echo "${historie_anzahlZuviele[*]}"

# Erstelle Tabelle/csv für Plot:

einkaufstage=`cat $istList | grep ^[0-9] | cut -d";" -f1 | tr "\n" ";"`
echo "Einkaufstage:	$einkaufstage"
fehlende_csv=`echo ${historie_anzahlFehlende[*]} | tr " " ";" | sed 's/$/;/g'`
echo "Fehlende:	$fehlende_csv"
zuviele_csv=`echo ${historie_anzahlZuviele[*]} | tr " " ";" | sed 's/$/;/g'`
echo "Zuviele:	$zuviele_csv"

echo "Schreibe csv nach: $outputPathCSV"
echo "Schreibe dat nach: $outputPath"

echo "" > $outputPath

for i in `seq 1 $(cat $istList | grep ^[0-9] | wc -l)`
do
	echo "$i ${historie_Einkaufstage[$i-1]} ${historie_anzahlZuviele[$i-1]} ${historie_anzahlFehlende[$i-1]} $(( ${historie_anzahlZuviele[$i-1]}+${historie_anzahlFehlende[$i-1]} ))" >> $outputPath
done

echo "Einkauf;Einkaufstag;Anzahl 'zuviel';Anzahl 'fehlend';Fehlersumme" > $outputPathCSV
cat $outputPath | sed 's/\ /;/g' >> $outputPathCSV


# Nutze Tabelle (dat-Datei) um Plots zu erstellen
echo "set terminal pngcairo
set output \"output_files/auswertung_$istList_Bezeichnung.png\"
set title \"\"
set xlabel \"Einkauf\"
set ylabel \"Fehler in Liste\"
set yrange [0:5]
set xtics 0,1,50
set xtics font \" ,8\"
plot \"$outputPath\" using 1:3 title \"'Zuviel' auf Liste\" lw 1 lc rgb \"green\",\
\"$outputPath\" using 1:4 title \"'Zu Wenig' auf Liste\" lw 1 lc rgb \"blue\",\
\"$outputPath\" using 1:5 title \"Fehlersumme\" lw 1 lt 1 lc rgb \"red\""> gnuplot_files/auswertung_$istList_Bezeichnung.plt

/usr/bin/gnuplot gnuplot_files/auswertung_$istList_Bezeichnung.plt