set terminal pngcairo
set output "output_files/auswertung_1113.png"
set title ""
set xlabel "Einkauf"
set ylabel "Fehler in Liste"
set yrange [0:5]
set xtics 0,1,50
set xtics font " ,8"
plot "./meta_files/auswertung_1113.dat" using 1:3 title "'Zuviel' auf Liste" lw 1 lt rgb "green","./meta_files/auswertung_1113.dat" using 1:4 title "'Zu Wenig' auf Liste" lw 1 lt rgb "blue","./meta_files/auswertung_1113.dat" using 1:5 title "Fehlersumme" lw 1 lt rgb "red"
