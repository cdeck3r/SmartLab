# Vorgehen

## Marktanalyse

Smart Shop bzw. Smart Home stellt den Einsatz von technischen Systemen, automatisierten Verfahren und vernetzten, ferngesteuerten Geräten in Einkaufsläden bzw. Wohnräumen und Häusern dar. Die Funktionen dienen hauptsächlich dazu, die Lebensqualität in irgendeiner Form zu erhöhen. Die Kommunikation und Steuerung der Smart Home und Smart Shop Technik erfolgt dabei über gängige Funkstandards wie WLAN, Bluetooth, ZigBee oder Z-Wave. Die zentrale Steuerungseinheit wird auch als Hub oder Gateway bezeichnet. Geräte starten, steuern und überwachen selbstständig je nach Szenario und Programmierung bestimmte Abläufe im Haushalt sowie in Supermärkten. Interoperabilität ist dabei das Zauberwort.   
Denn bspw. schält sich die Heizung nur aus, wenn das Fenster geöffnet wird. Ist keine Interoperabilität zwischen den Elementen gegeben, ist das Home bzw. der Shop schlichtweg nicht smart. 

Auf dem Markt existiert eine Vielzahl an Herstellern, die IOT-Devices für die unterschiedlichsten Bereiche wie z.B. Smart Home oder Smart Shop anbieten. Aktuelle 'Big Player' im Smart Home Bereich sind bspw. Amazon, Bosch und Google.   
In der folgenden Tabelle sind einige Geräte beispielhaft aufgeführt:

<table>
  <thead>
    <tr>
      <th style="text-align:left">Smart Home Geräte</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left">
        <ul>
          <li>Sprachassistenten</li>
          <li>Lichtsysteme</li>
          <li>Thermostate</li>
          <li>Sicherheitssysteme, wie z.B.:
            <ul>
              <li>Überwachungskameras</li>
              <li>Rauchmelder</li>
              <li>Schließsysteme</li>
              <li>Warn- und Signalgeräte</li>
            </ul>
          </li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>Im Vergleich zum Smart Home Bereich enthält der Smart Shop Bereich bisher eine viel kleinere Produktpalette. Ein paar wenige beispielhafte Geräte die in der heutigen Zeit bereits eingesetzt werden, sind in der folgenden Tabelle dargestellt:

<table>
  <thead>
    <tr>
      <th style="text-align:left">Smart Shop Geräte</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="text-align:left">
        <ul>
          <li>Smart Screens</li>
          <li>digitale Preisschilder</li>
          <li>smarte Kassensysteme</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>### Technologien und Standards

Als Teil eines IoT-Ökosystems sind Machine-to-Machine \(M2M\) Dienste einer der wichtigsten Bereiche, in denen die Automatisierung von Geschäftsprozessen stattfindet. Um die Kommunikation zwischen den Maschinen zu ermöglichen, wurde eine breite Palette von Technologien entwickelt, um verschiedensten Anforderungen wie Energieverwaltung, Geschwindigkeit, Komplexität usw. gerecht zu werden.  
Die folgende Klassifizierung zeigt, welche Protokolle für die Infrastruktur, Identifikation, Kommunikation und Transport, für den Datentransfer sowie für Device Management und Semantic verwendet werden können.

| Layer | Protokolle |
| :--- | :--- |
| **Infrastruktur** | 6LowPAN, IPv4/IPv6, RPL |
| **Identifikation** | EPC, uCode, IPv6, URIs |
| **Kommunikation und Transport** | Wifi, Bluetooth, LPWAN, ZigBee, Z-Wave, Smart Beacons |
| **Datenübertragung** | MQTT, CoAP, AMQP, Websocket, Node, REST |
| **Geräteverwaltung** | TR-069, OMA-DM |
| **Semantik** | JSON-LD, Web Thing Model |

Die größte Unsicherheit der potentiellen Kunden von IOT-Geräten besteht oftmals in der Inkompatibilität von Geräten der unterschiedlichen Hersteller. Die Inkompatibilität ist auf Transport-Ebene den unterschiedlichsten Protokollen zu finden. Hier konkurrieren Funkprotokolle wie bspw. Zigbee, Z-Wave, Wifi, Bluetooth, EnOcean, uvm. miteinander. Zusätzlich stehen die vereinzelt eingesetzten kabelgebundenen Systeme wie Powerline-basierte Lösungen in Konkurrenz. Dieser Protokoll-„Wirrwarr“ macht es für den Endkunden wirklich schwierig für seinen Bereich und den eigenen Wünschen eine entsprechend optimierte Lösung zu finden, da der Großteil der Systeme schlichtweg nicht miteinander kompatibel sind.

IOT-Hersteller bemühen sich jedoch nicht um einen einheitlichen Standard, sondern entwickeln meist proprietäre Protokolle und Standards. Dies hat dazu geführt, dass es unzählige ‚Standards‘ und Protokolle im Smart Home Bereich gibt.  Durch die vielen oftmals auch ‚kleinen‘ Anbieter hat sich der Trend entwickelt, dass für jedes smarte Gerät auch eine entsprechende Bridge erworben werden muss, um das bzw. die Geräte ansteuern zu können. D.h. werden in einem Haushalt Produkte von einem verschiedenen Hersteller verwendet, müssen i.d.R. auch entsprechend viele Bridges erworben werden.  
Eine einheitliche Steuerung der Geräte ist oftmals überhaupt nicht möglich, da sich die Plattformen welche zur Steuerung verwendet werden in ihren Protokollstandards ebenfalls unterscheiden können. Um dieses Problem zu beheben gibt es verschiedene Möglichkeiten:

1. Schaffung eines einheitlichen Standards  \(auf Transportebene – d.h. einheitliches Netzwerkprotokoll, Pairing, Bereitstellung von Services, …\)
2. Verwendung einer Integrationsplattform  \(Vereinigung der verschiedenen Standards durch entsprechende Adapter – Übersetzung der verschiedenen Protokolle in 'einheitliche Sprache‘.\)
3. Eine Kombination aus 1\) und 2\)

### Touchpoints zwischen Smart Home und Smart Shop

 Die Verknüpfung von Smart Home und Smart Shop ist nicht trivial, da die beiden Bereiche i.d.R. nichts miteinander zu tun haben. Mit Verknüfung ist gemeint, die beiden Bereiche in irgendeiner Art zusammenzuführen, bzw. durch die Ergänzung des einen durch den anderen einen Mehrwert zu erzeugen.

**Definition übergreifende und ergänzende Anwendung**

Werden Smart Home und Smart Shop in einer Anwendung vereint, das heißt dass beide Bereiche für eine bestimmt Anwendung dienen, kann zwischen übergreifenden und ergänzenden Anwendungen unterschieden werden. **Übergreifende Anwendungen** entsprechen dabei Anwendungen, welche sich an Services aus zwei bzw. in unserem Fall beiden Bereichen, also Smart Home und Smart Shop, bedienen \(wie in _Abbildung 2.2_ zu sehen\).  **Ergänzende Anwendungen** sind Anwendungen, welche durch eine andere Anwendung an Funktionen erweitert bzw. ergänzt werden, dabei der Informationsfluss jedoch nur in eine Richtung fließt.

![Abbildung 2.1: Erg&#xE4;nzende Anwendung](.gitbook/assets/ergaenzende-app.png)

Ein Beispiel für eine übergreifende Anwendung: Inventarmanagement im Home sowie im Shop-Bereich, d.h. Verknüpfung der Informationen zur Bereitstellung von übergreifenden Services, wie z.B.:

* eine App-Angebotsbenachrichtigung  für einen definierten Produktbestand des Smart Home‘s
* Benachrichtigung des Smart Shops über Kaufprognosen anhand der Informationen des Smart Home‘s

_Abbildung 2.1_ stellt die Beziehung zwischen Smart Home und Smart Shop im Kontext einer ergänzenden Anwendung dar. Dabei ergänzt in Beispiel 1 der Smart Shop das Smart Home, indem Angebotsbenachichtigungen für ausgewählte Produkte an den Kunden in sein Eigenheim gesendet werden. In Beispiel 2 ergänzt das Smart Home den Smart Shop, indem die im Smart Home angelegte Einkaufsliste im Shop abgerufen werden kann. 

![Abbildung 2.2: &#xDC;bergreifende Anwendung](.gitbook/assets/uebergreifende-app.png)

Diese grundlegende Unterscheidung zwischen einer übergreifenden und einer ergänzenden Anwendung wurde benötigt, um uns von vorhandenen Anwendungen und Techniken abgrenzen zu können. Übergreifende Anwendungen sind nämlich noch nicht, bzw. zumindest nicht in der breiten Masse vorhanden. Ergänzende Anwendungen hingegen sind bereits einige zu finden.

## Entwurf

Idee war es, eine übergreifende Anwendung zu implementieren, die den Einkaufsprozess und das Inventarmanagement sowohl für Haushalte als auch für Retail vereinfacht bzw. unterstützt. 

**Anwendung:** Intelligente digitale Einkaufslisten-Applikation mit folgenden Funktionen:

* dynamische Anpassung der Einkaufsliste
* \(Teil-\)automatisiertes Befüllen der Einkaufsliste
* Kategorisierung der Produkte nach Dringlichkeit

Diese Funktionen werden anhand folgender Informationen realisiert:

* Verbrauchs- und Kaufverhalten des Users
* Statistische Haltbarkeitsdaten

Im Folgenden werden die für eine Implementierung auftretenden Fragen beantwortet. 

### Wie wird die Wahrscheinlichkeit ob ein Produkt benötigt wird basierend auf Verbrauchs- und Haltbarkeitsdaten bestimmt?

Um eine automatisierte Verwaltung der Einkaufsliste zu ermöglichen, muss die Wahrscheinlichkeit bestimmt werden können, ob ein bestimmtes Produkt bereits verbraucht ist oder nicht. Dies kann auf verschiedenste Art und weise realisiert werden. Da wir diese Problematik mit dem Hintergrund der Realisierung einer Einkaufslisten Applikation untersuchen, kann das Szenario wie in _Abbildung 2.3_ dargestellt werden.

![Abbildung 2.3: Ablauf&#xFC;bersicht](.gitbook/assets/uebersicht_rq1.PNG)

* **Smart Home**: Erfassung von Verbrauchsdaten über Smart Home Geräte \(Bspw. Alexa\).
* **Smart Shop**: Erfassung von Ablaufdaten von Produkten / Produktgruppen und Lieferung von Einkaufsdaten eines Verbrauchers über Smart-Shop-Geräte.
* **Einkaufslisten Applikation**: Zusammenführung von Ablauf- und Verbrauchsdaten mit dem Einkauf und Bestimmung der Wahrscheinlichkeiten der Produktnachfrage und Verwendung von Wahrscheinlichkeiten zum automatischen Füllen der Einkaufsliste nach definierten Regeln.

Die Grundidee besteht darin, mithilfe der gesammelten Daten eine Wahrscheinlichkeitsfunktion \(Dichtefunktion\) zu erstellen. Es gibt jedoch verschiedene Möglichkeiten, eine geeignete Wahrscheinlichkeitsfunktion zu bestimmen. Die Auswahl einer geeigneten Wahrscheinlichkeitsfunktion hängt von der Streuung bzw. Verteilung der Daten ab. 

Die einfachste Möglichkeit besteht darin, die Wahrscheinlichkeitsfunktion zu erstellen, indem die **relative Häufigkeit** der Daten verwendet wird. Sind nur wenige Daten verfügbar, kann die Wahrscheinlichkeitsfunktion sehr ungenau sein. Das heisst umso weniger Daten verfügbar sind, desto ungenauer wird die Prognose. Um eine bessere Prognose zu erstellen, wenn wenige Daten vorliegen, kann eine **Approximationsfunktion** zur Erreichung genauerer Ergebnisse verwendet werden. Diese könnten zum Beispiel sein:

* Uniformerteilung
* Normalverteilung
* Logarithmische Normalverteilung
* Dreiecksverteilung
* ...

Die erwähnten Approximationsfunktionen sind nur ein kleiner Teil aller existierenden Wahrscheinlichkeitsfunktionen. Deren Verwendung ist allerdings nur dann sinnvoll, wenn die vorliegenden Datenverteilung mit einer gewählten Approximationsfunktion korreliert. Andernfalls könnten die Ergebnisse stark gefälscht werden.  
Um zu überprüfen, ob eine Approximationsfunktion der gegebenen Datenverteilung ähnlich ist, gibt es verschiedene Möglichkeiten. Diese können z.B. sein:

* Optische Prüfung \(Vergleich beider Funktionen optisch\)
* Kolmogorow-Smirnow-Test
* Anderson-Darling-Test
* Chi-Quadrat-Test

Zusammenfassend ergeben sich somit zwei alternative Vorgehensweisen, wie in _Abbildung 2.4_ dargestellt.

![Abbildung 2.4: Vorgehensweise zur Wahrscheinlichkeitsbestimmung](.gitbook/assets/wahrscheinlichkeitsbestimmung.PNG)

In unserer Anwendung haben wir die Wahrscheinlichkeitsverteilung anhand der relative Häufigkeit der vorliegenden Daten erzeugt. Diese Entscheidung wurde gefällt, da die uns vorliegenden Daten nicht mit einer der allgemeinen Wahrscheinlicheitsfunktionen  korrelierten.

### Wie kann Smart Shop die Qualität der Einkaufslisten Applikation verbessern?

Durch die Integration von Smart Shop, können eine Vielzahl an zusätzliche Services für die Einkaufslisten Applikation entwickelt werden. Grundlage dafür wäre eine vorhandene Infrastruktur auf der Seite von Smart Shop, die mittels Schnittstelle zur Applikation eine Kommunikation ermöglicht.   
Im Folgenden einige Beispiele von möglichen Verbesserungen :

* **Kundenverbrauchsdaten-Service** Nutzt ein User die Einkaufslisten Applikation, kann er sich im Shop mittels App authentifizieren. Dies könnte bspw. durch ein smartes Kassensystem ermöglicht werden. Zusätzlich könnte so das Datum der gekauften Produkte vom Shop erfasst und and die App übermittelt werden. Ein intelligentes Kassensystem könnte somit bereits die folgenden Funktionen enthalten:
  * Möglichkeit für Kundenanmeldung via QR-Code
  * Anzeige aller Produkte die abgescannt oder ausgewählt wurden
  * Anzeige von Produktpräferenzen des Kunden
  * Möglichkeit Produkte via Barcode zu erfassen
  * Nach dem Bezahlvorgang :

    * Bereinigung der  Einkaufsliste
    * Erfassung des Kaufdatums der Produkte
* **Angebotsbenachrichtigungen** Basierend auf dem Kaufverhalten oder/und der Einkaufsliste des Users können personalisierte Angebotsbenachrichtungen über die Applikation angezeigt werden und somit den Einkauf des Kunden optimieren. ****
* **Kundenprofile** Um den User bei der erstmaligen Verwendung der Einkaufslisten Applikation schnell und effizient zu unterstützen, könnten vom Shop vordefinierte Kundenprofile angeboten werden, die das Kundenverhalten angenähert abbilden sollen. Somit kann bei \(noch\) nicht vorhanden Statistiken des Kundenverhaltens, trotzdem bereits eine automatisierte Einkaufsliste erstellt werden. Korreliert ein vordefiniertes Profil nicht voll und ganz mit dem Kundenprofil, kann der Kunde sein Profil selbst ergänzen, indem der die App durch manuelles Hinzufügen von Produkten in die Einkaufsliste, mit Verbrauchsinformationen füttert. 
* **Einkaufslisten-basierte Navigation** Durch die Integration einer 'In-Shop-Navigation' könnte der Nutzer basierend auf seiner Einkaufsliste optimiert durch den Laden geführt werden. Dies hätte vor allem für Kunden sinnvoll, die sich im Shop \(noch\) nicht gut auskennen.

Die vorgestellten Ideen sind nur ein Teil vieler weiterer Möglichkeiten von Optimierungen der Applikation via Smart Shop. Da unser Projektziel es war, eine ergänzende Anwendung zu entwickeln, haben wir eine Smart Shop integrierende Applikation in Form eines intelligenten Kassensystems entwickelt. Die genauen Spezifikationen sind im Kapitel Implementierung zu finden.

### Wie kann Smart Home die Qualität der Einkaufslisten Applikation verbessern?

Der Smart Home Bereich hat durch seine rasante Entwicklung heute bereits einen guten Entwicklungstand erreicht. Betrachtet man das Inventarmanagement für Haushaltsprodukte, kann man sehr schnell einige Smart Home Geräte nennen, die dies unterstützen. Von einer smarten Kaffeemaschine die den Kaffeestand automatisch meldet, über einen intelligenten Kühlschrank, der per Touchscreen bedienbar ist, um bspw. den Bestand zu erfassen, bis hin zur Verwaltung via Sprachassistenten wie Amazon Alexa oder Google Home. Somit ergeben sich aus der Smart Home Integration viele Integrationsmöglichkeiten.   
Dabei kann man zwischen zwei unterschiedlichen Integrationsaspekte unterscheiden:

* **Integration mit User-Eingriff \(manuelle Datenerfassung\)**  Hierbei handelt es sich um eine Verknüpfung der Einkaufslisten Applikation mit einem Smart Home Gerät, wobei der User eingreifen muss. Fehlt bspw. ein Produkt auf der Einkaufsliste kann der User per Sprachsteuerung dieses Produkt der Liste hinzufügen. Durch dieses Ereignis wird zudem ein nach ein Datensatz mit dem genauen Verbrauchsverhalten für dieses Produkt angelegt. Je öfter diese Aktivität ausgeführt wird \(manuell oder automatisiert\), desto besser wird die Qualität der durch die Applikation generierten Einkaufsliste. Dieser Schritt ist ebenfalls direkt über die Applikation möglich, jedoch weniger komfortabel.
* **Integration ohne User-Eingriff \(automatische Datenerfassung\)**  In diesem Fall meldet das Smart Home Gerät den Verbrauch selbstständig. D.h. es handelt sich um eine automatische Datenerfassung. Eine smarte Kaffeemaschine kann bspw. den Bedarf an Kaffeebohnen melden, wenn der Füllstand einen bestimmten Schwellenwert unterschritten hat. Somit kann anhand dieser Daten, analog zu der manuellen Datenerfassung, die automatische Einkaufsliste erzeugt werden.

Es sind weitere Szenarien vorstell- bzw. umsetzbar, in denen mit Hilfe von Smart Home Geräten die Einkaufslisten Applikation optimiert wird. Es ist klar zu erkennen, dass eine gut ausgestattete Smart Home Umgebung \(kann u.a. durch die Anzahl smarter Geräte beurteilt werden\) ein Hinweis über den Qualitätsgrad geben könnte. Je höher der Qualitätsgrad der Smart Home Umgebung, desto höher die Genauigkeit der generierten Einkaufsliste. Zudem werden je nach Qualitätsgrad der Smart Home Umgebung die Daten deutlich schneller in ihrer Qualität verbessert.

### Fusion von Smart Home und Smart Shop:

Zu guter letzt betrachten wir die Möglichkeiten die durch die Fusion von Smart Home und Smart Shop bzw. durch die Integration beider in Bezug auf die Einkaufslisten Applikation entstehen.   
Die entwickelte übergreifende Anwendung wurde im Laufe des Projekts stufenweise aufgebaut. Dies wird durch das folgende Kapitel durch die drei Architekturstufen verdeutlicht.

#### Architektur

**Stufe 1**

![Abbildung 2.5: Architektur&#xFC;bersicht Stufe 1](.gitbook/assets/1.PNG)

Stufe 1 unserer Applikation, dargestellt in _Abbildung 2.5,_ bildet die Grundlage für alle weiteren im Folgenden hinzukommenden Funktionen. In dieser Architekturebene beschränkten wir uns lediglich auf die mobile Applikation ohne jegliche Anbindung von Smart Home oder Shop, d.h. es handelt sich weder um eine übergreifende, noch um eine ergänzende Anwendung.  
In dieser Architekturebene gibt der Benutzer seinen Standard-Einkaufswagen sowie sein Verbrauchsverhalten \(durch Setzen eines Produkts auf die Einkaufsliste\) manuell in die App ein. Statistische Grundlage zum automatisierten Füllen der Einkaufsliste bilden dabei die standardmäßig vorhandenen MHD-Statistiken sowie die manuell erfassten Verbrauchsdaten.

#### Architektur: Stufe 2

![Abbildung 2.6: Architektur&#xFC;bersicht Stufe 2](.gitbook/assets/2%20%281%29.PNG)

Die in _Abbildung 2.6_ dargestellte Architektur der Stufe 2 setzt auf Stufe 1 auf und ergänzt dieses durch die Smart Shop Komponente. Durch die Integration von Smart Shop entstehen die im vorigen Kapitel beschriebenen Möglichkeiten. Wir entschieden uns zur Implementierung einer prototypischen Applikation eines smarten Kassensystems.  
Die Einbindung von Smart Shop in Stufe 2 verbessert unsere Applikation zu einer ergänzenden Anwendung.   
In dieser Architekturebene wird somit ein weiterer Kanal zur Verbesserung der Verbrauchsstatistik erschaffen, da automatisiert die Einkaufsliste geleert wird und zudem Kaufinformationen automatisiert Rückschluss auf Verbrauchsverhalten liefern können.  
Wird beim Einkauf die Zahlung abgeschlossen, werden die Kaufinformationen des Users vom Smart Shop-Bereich in die Verbrauchsdatenbank übermittelt. Diese Daten werden wie bereits in Stufe 1 verwendet, um das Füllen und Leeren der Einkaufsliste zu automatisieren. 

#### Architektur: Stufe 3

![Abbildung 2.7: Architektur&#xFC;bersicht Stufe 3](.gitbook/assets/3%20%281%29.PNG)

Die dritte und letzte Architekturstufe \(_Abbildung 2.7_\) wird im Vergleich zur Stufe 2 zusätzlich durch die Smart Home Komponente\(n\) ergänzt. Durch diese Erweiterung entwickelt sich die zuvor ergänzende Anwendung zu einer übergreifenden Anwendung. Durch die Ergänzung des Smart Home Bereiches können zusätzliche Echtzeitverbrauchsdaten des Verbrauchers erhoben und in die Verbrauchsstatistik aufgenommen werden. Durch diesen weiteren Kanal bzw. die weiteren Kanäle aus dem Smart Home, können somit genauere Prognosen über den Verbrauch bestimmt bzw. eine bessere Einkaufsliste generiert werden.  










