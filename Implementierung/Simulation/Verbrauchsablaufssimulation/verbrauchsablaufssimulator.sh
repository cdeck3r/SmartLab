#!/bin/bash
#
# Erzeugt für vorgegebene Verbrauchsranges eine zufälige Abfolge/einen zufälligen Verbrauch
#
#

#inputfile=input_Verbrauchsrange
inputfile=$2
#outputfile=output_Verbrauchsverhalten
outputfile=$3
#countDatacolumns=10
countDatacolumns=$1

if [[ $# -lt 3 ]] || [[ ! -f $inputfile ]] 
then
   echo "Usage: verbrauchsablaufssimulator.sh <anzahl_gewuenschter_Datensaetze> <inputfile> <outputfile>"
   exit 1
fi 

for product in `cat $inputfile | awk '{print $1}'`
do
   echo "product: $product"
   min=`cat $inputfile | grep $product | awk '{print $2}'`
   max=`cat $inputfile | grep $product | awk '{print $3}'`
   #echo "min: $min"
   #echo "max: $max"
   
   range=$(( $max - $min ))
   #echo "range= $range"
   
   echo -n "$product " >> $outputfile
   
   i=0
   while [[ i -lt $countDatacolumns  ]]
   do
      randomVerbrauch=$(( $RANDOM % $range + min ))
      echo -n "$randomVerbrauch " >> $outputfile
      i=$((i+1))
   done   
   echo '' >> $outputfile   
done
