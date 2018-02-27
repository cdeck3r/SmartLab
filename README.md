# SmartLab

Studentenprojekt welches die Kombination und das Zusammenspiel von Smart Shop und Smart Home untersucht; Die Studie erfolgt im Rahmen des Masterprogramms Services Computing am HHZ.

Student project researching the intersection between Smart Shop and Smart Home; part of the Masters programme Services Computing at HHZ. 

# Inhaltsangabe
- [Allgemein](#allgemein)
  * [Einordnung](#einordnung)
    + [Smart Home](#smart-home)
    + [Smart Shop](#smart-shop)
    + [Verknüpfung von Smart Home und Smart Shop](#verknüpfung-von-smart-home-und-smart-shop)
      - [Definition übergreifende und ergänzende Anwendung](#definition-übergreifende-und-ergänzende-anwendung)
  * [Standards](#standards)
- [Marktanalyse](#marktanalyse)
  * [Smart Home Bereich](#smart-home-bereich)
    + [Amazon](#amazon)
      - [Platform](#platform)
      - [Geschaeftsmodell](#geschaeftsmodell)
      - [Produktpalette](#produktpalette)
      - [Protokolle](#protokolle)
      - [Smart-Home-Partner](#smart-home-partner)
      - [Kompatibilitaet](#kompatibilitaet)
    + [Apple](#apple)
      - [Platform](#platform-1)
      - [Geschaeftsmodell](#geschaeftsmodell-1)
      - [Produktpalette](#produktpalette-1)
      - [Protokolle](#protokolle-1)
      - [Kompatibilitaet](#kompatibilitaet-1)
    + [Bosch bzw. BSH](#bosch-bzw-bsh)
      - [Platform](#platform-2)
      - [Geschaeftsmodell](#geschaeftsmodell-2)
      - [Produktpalette](#produktpalette-2)
      - [Protokolle und Kompatibilität](#protokolle-und-kompatibilität)
      - [Sicherheit](#sicherheit)
    + [Google](#google)
      - [Platform](#platform-3)
      - [Geschaeftsmodell](#geschaeftsmodell-3)
      - [Produktpalette](#produktpalette-3)
      - [Protokolle](#protokolle-2)
      - [Kompatibilitaet](#kompatibilitaet-2)
    + [Quivicon](#quivicon)
      - [Platform](#platform-4)
      - [Geschaeftsmodell](#geschaeftsmodell-4)
      - [Produktpalette](#produktpalette-4)
      - [Protokolle](#protokolle-3)
      - [Sicherheit](#sicherheit-1)
      - [Kompatibilitaet](#kompatibilitaet-3)
    + [eQ-3](#eq-3)
      - [Platform](#platform-5)
      - [Geschaeftsmodell](#geschaeftsmodell-5)
      - [Produktpalette](#produktpalette-5)
      - [Protokolle](#protokolle-4)
      - [Kompatibilitaet](#kompatibilitaet-4)
    + [Afriso](#afriso)
      - [Platform](#platform-6)
      - [Geschaeftsmodell](#geschaeftsmodell-6)
      - [Produktpalette](#produktpalette-6)
      - [Protokolle](#protokolle-5)
      - [Sicherheit](#sicherheit-2)
      - [Kompatibilitaet](#kompatibilitaet-5)
  * [Smart Shop Bereich](#smart-shop-bereich)
    + [Einleitung](#einleitung)
    + [Technologien](#technologien)
      - [Digitale Preisschilder](#digitale-preisschilder)
      - [Smart Bluetooth / Beacons](#smart-bluetooth---beacons)
      - [Smart Screens (bzw. Gesichtserkennung etc.)](#smart-screens--bzw-gesichtserkennung-etc-)
      - [Amazon Go / Sensor Fusion](#amazon-go---sensor-fusion)
    + [Ausblick (?)](#ausblick----)
  * [Trends](#trends)

# Allgemein
## Einordnung
### Smart Home
Der IOT- bzw. Smart-Home-Markt ist mittlerweile so breit aufgestellt, dass die Smart-Home-Anbieter meist nicht das ganze Spektrum an Smart-Home-Geräten anbieten können.
Diese Problematik entstand durch einige Einflussfaktoren. Dazu gehören bspw. der schnelle Wachstum, wodurch Anbieter schwer hinterherkommen, das fehlende Knowhow in entsprechenden Bereichen, da der Markt sehr umfangreich ist, und vor allem ein gemeinsamer Standard, welcher die Kompatibilität zwischen Geräten verschiedener Hersteller möglich machen würde.
Die Anwendung von Smart-Home-Geräten steigt stetig, jedoch hält sich die Akzeptanz der Geräte in Grenzen. Dies ist in den meisten Fällen der Unsicherheit geschuldet, welche bspw. durch eine Vielzahl unterschiedlicher Anbieter und den sich sehr unterscheidenden Protokollen ohne einheitlichen Standard entsteht. Diese Unsicherheit besteht sicherlich auch durch die bisherige Undurchsichtigkeit der Kompatibilitäten und Standards. D.h. dadurch dass sich bisher noch kein Standard richtig durchgesetzt hat, werden Smart-Home-Vorrichtungen vorsichtshalber noch nicht installiert, da die Erweiterbarkeit, sowie die Wartbarkeit für zukünftige Anpassungen des Smart Homes nicht garantiert sind.

### Smart Shop

### Verknüpfung von Smart Home und Smart Shop
<Lorem Ipsum> Die Verknüpfung von Smart Home und Smart Shop ist nicht trivial, da die beiden Bereiche i.d.R. nichts miteinander zu tun haben.
Mit Verknüfung ist gemeint, die beiden Bereiche in irgendeiner Art zusammenzuführen, bzw. durch die Ergänzung des einen durch den anderen einen Mehrwert zu erzeugen. 
</Lorem Ipsum>

#### Definition übergreifende und ergänzende Anwendung
Werden Smart Home und Smart Shop in einer Anwendung vereint, d.h. dass beide Bereiche für eine bestimmt Anwendung dienen, kann zwischen übergreifenden und ergänzenden Anwendungen unterschieden werden.
**Übergreifende Anwendungen** entsprechen dabei Anwendungen, welche sich an Services aus zwei bzw. in unserem Fall beiden Berichen, also Smart Home und Smart Shop, bedienen. (siehe Abbildung)
**Ergänzende Anwendungen** sind Anwendungen, welche durch eine andere Anwendung an Funktionen erweitert bzw. ergänzt werden, dabei der Informationssfluss jedoch nur in eine Richtung fließt.

![uebergreifende-App.png](https://github.com/cdeck3r/SmartLab/blob/master/uebergreifende-App.png)

Ein Beispiel für eine übergreifende Anwendung:
Inventarmanagement im Home sowie im Shop-Bereich,
d.h. Verknüpfung der Informationen zur Bereitstellung von übergreifenden Services, wie z.B.
* eine Angebotsbenachrichtigung in einer App für einen definierten 	Produktbestand des Smart Home‘s
* Benachrichtigung des Smart Shops über Kaufprognosen anhand der 	Informationen des Smart Home‘s


Die nachfolgende Grafik stellt die Beziehung zwischen Smart Home und Smart Shop im Kontext einer ergänzenden Anwendung dar.
Dabei ergänzt in Beispiel 1 der Smart Shop das Smart Home, indem Angebotsbenachichtigungen für ausgewählte Produkte an den Kunden in sein Eigenheim gesendet werden.
In Beispiel 2 ergänzt das Smart Home den Smart Shop, indem die im Smart Home angelegte Einkaufsliste im Shop abgerufen werden kann.
![ergaenzende-App.png](https://github.com/cdeck3r/SmartLab/blob/master/ergaenzende-App.png)

Diese grundlegende Unterscheidung zwischen einer übergreifenden und einer ergänzenden Anwendung wird benötigt, um uns von vorhandenen Anwendungen und Techniken abgrenzen zu können, denn übergreifende Anwendungen sind noch nicht, bzw. zumindest nicht in der breiten Masse vorhanden. Ergänzende Anwendungen von Smart Home und Smart Shop sind allerdings bereits einige vorhanden.


## Standards
Wie in der zuvor beschriebenen Einordnung zu erkennen entsteht die größte Unsicherheit der potentiellen Kunden von Smart-Home-Geräten oftmals in der Inkompatibilität von Geräten von unterschiedlichen Herstellern.
Die Inkompatibilität ist auf Transport-Ebene den unterschiedlichsten Protokollen zuzuschreiben. Hier konkurrieren Funkprotokolle wie bspw. Zigbee, Z-Wave, Wifi, Bluetooth, EnOcean, uvm. miteinander. Zusätzlich stehen die vereinzelt eingesetzten kabelgebundenen Systeme wie Powerline-basierte Lösungen in Konkurrenz.
Dieser Protokoll-„Wirrwarr“ macht es für den Endkunden wirklich schwierig für seinen Heimbereich und den eigenen Wünschen eine entsprechend optimierte Lösung zu finden, da der Großteil der Systeme schlichtweg nicht miteinander kompatibel sind. 
Die Smart-Home-Hersteller bemühen sich jedoch nicht wirklich um einen eigenen Standard, sondern entwickeln meist proprietäre Protokolle und Standards. D.h. sie machen oft ‚ihr eigenes Ding‘ indem Sie unabhängig vom Rest des Marktes eigene Standards ins Leben rufen. Dies hat dazu geführt, dass es unzählige ‚Standards‘ und Protokolle im Smart Home Bereich gibt.
Für den Endanwender bringt dies einige Probleme mit sich, wenn man sein Haus ‚smart‘ machen will ohne sich dabei auf Produkte eines Herstellers beschränken zu müssen. Durch die vielen oftmals auch ‚kleinen‘ Anbieter hat sich der Trend entwickelt, dass für jedes smarte Gerät auch eine entsprechende Bridge erworben werden muss, um das bzw. die Geräte ansteuern zu können. D.h. werden in einem Haushalt Produkte von einem verschiedenen Hersteller verwendet, müssen i.d.R. auch entsprechend viele Bridges erworben werden. Eine einheitliche Steuerung der Geräte ist oftmals überhaupt nicht möglich, da sich die Plattformen welche zur Steuerung verwendet werden in ihren Protokollstandards ebenfalls unterscheiden können.
Um dieses Problem zu beheben gibt es verschiedene Möglichkeiten:
1.	Schaffung eines einheitlichen Standards (auf Transportebene – d.h. einheitliches Netzwerkprotokoll, Pairing, Bereitstellung Fähigkeiten, …)
2.	Verwendung einer Integrationsplattform (Vereinigung der verschiedenen Standards durch entsprechende Adapter – Übersetzung der verschiedenen Protokolle in ‚Einheitssprache‘.)
3.	Eine Kombination aus 1) und 2)

# Marktanalyse
## Smart Home Bereich

### Amazon
#### Platform
Durch Öffnung des intelligentem Sprachassistenten „Alexa“, errichtet Amazon eine Plattform für fremde Hersteller von Smart Home Produkten. Um die Vision des vernetzten und komfortablen mit Sprachbefehlen steuerbaren smarten Zuhauses zu verwirklichen bzw. ein Schritt näher zu bringen. Amazon möchte in Zukunft mit Alexa eine große Rolle in Home Automation Business einnehmen und bietet den Smart Home Produkthersteller eine standardisierte Programmierschnittstelle (API) an, für den Zugriff auf den Sprachassistenten.

#### Geschaeftsmodell
Amazon sieht in Alexa ganz trivial einen weiteren Kanal, Produkte zu verkaufen.  Denn die wichtigsten Prozesse laufen auf den Servern der Unternehmen ab. Anfragen an Alexa werden über die Server geschleust. 
Somit entsteht ein weiterer Layer zwischen Endkunde und Drittanbietern. Den Anbietern wird der Zugang zum System gewährt und diese können dann ihre Applikationen anbieten, in diesem Fall „Skills“. Zusätzlich können Smarthome-Geräte, Fahrzeuge und andere Alltagsgegenstände mit dem System vernetzt werden.
Dadurch erhält Amazon uneingeschränkten Zugriff auf digitale Informationen. Es werden also während der Nutzung von Alexa kontinuierliche Daten gesendet und archiviert. Über diese Daten hat der Nutzer nur wenig Kontrolle. Durch diese gesammelten Daten kann Amazon ein genaues Profil über den Nutzer erstellen und durch gezielte Angebote den Kunden locken Produkte zu kaufen. Diese Kundenprofile können von Amazon in naher Zukunft noch weiter optimal eingesetzt werden. Beispielsweise in den geplanten „Amazon Go“ Märkten.

#### Produktpalette
* Amazon Echo Plus (149,99 €)
* Amazon Echo (99,99 €)
* Amazon Echo Dot (34,99 €)
* Amazon Prime Mitgliedschaft verspricht zusätzlich Vorteile bei der Nutzung von Alexa, beispielsweise können verfügbare Produkte erneut bestellt werden

#### Protokolle
Amazon Alexa und die mit Alexa gekoppelten SmartHome-Geräte kommunizieren über WLAN. Also sind alle Geräte mit einem zentralen Gateway verbunden. Natürlich im selben Netzwerk. Die Intelligenz, damit Alexa die SmartHome Geräte ansteuern kann erfolt über API.

#### Smart-Home-Partner
*	Deutsche Telekom
*	Spotify
*	Phillips (Smarte Lampen)
*	Innogy ( bspw. Tür – und Fenstersensoren)
*	Tado (Smarte Thermostate)
*	Wemo (Intelligente Steckdosen)


#### Kompatibilitaet
Um eine universelle Sprachsteuerung von Smart Home Devices durch Alexa zu ermöglichen, stelle Amazon das Interface API zu Verfügung. Die Software ist neuer Bestandteil des so genannten „Alexa Skills Kit“ und wird als „Smart Home API“ bezeichnet.
Die API erschafft eine standardisierte Schnittstelle für Programmierer von Smart Home Produkten. Über diese Schnittstelle können sie auf die verschiedenen Funktionen von Alexa zugreifen. Dadurch kann der Sprachassistent in die Lage versetzt werden, wenn er gefragt wird, die verschiedenen Geräte zu steuern. Die Intelligenz verlagert sich gleichzeitig in Richtung Alexa und die Komplexität und Hardwareanforderungen an die Smart Home Geräte sinken.
Durch API lass sich Funktionen für die Sprachsteuerung wesentlich schneller und einfacher in die Smart Home Devices integrieren. Zusätzlich lässt sich für die komplette per Alexa sprachgesteuerte Smart Home Welt ein einheitlicher Wortschatz verwenden.

* Vorteil für Smart Home Gerätehersteller,  einheitliche Sprachbefehle werden von ihre Devices über die API verstanden, ohne selbst die nötige Intelligenz zu besitzen
* Vorteil für den User, nur wenige Befehle für die Steuerung der unterschiedlichen Funktionen des smarten Zuhauses nötig.


### Apple
#### Platform
Apple's aktuelle Smart Home Platform nennt sich Apple HomeKit und stellt eine zentrale Platform zur Steuerung von Smart Home Geräten dar.
Diese Platform stellt ein von Apple entwickeltes Protokoll zur Kommunikation mit Smart Home Geräten bereit. Um Geräte in diese Platform einzubetten bzw. die Protokolle in Ihren Geräten zu integrieren ist eine Zertifizierung auf Seiten von Apple notwendig, um die volle Funktionalität des Apple HomeKits verwenden zu können.

Apple Geräte wie Iphone, Ipad oder AppleTV fungieren im Apple HomeKit als Steuerungseinheit. D.h. jegliche Steuerungssignale an das Smart Home können von den Apple Geräten über den Tocuhbildschirm, Tastatureingabe oder auch die Spracheingabe abgesetzt werden und dienen somit als smarte Fernbedienung für das Smart Home.

#### Geschaeftsmodell
Apples Geschäftsmodell für den Smart Home Bereich unterscheidet sich von den meisten Ansätzen. Die Platform Apple HomeKit kann auf Apple-Geräten betrieben werden und entsprechende Smart Home Geräte oder Sensoren können mit ihnen verbunden werden. Als Fernsteuerung des Smart Homes dient dabei dann bspw. ein Iphone, ein Ipad oder auch ein AppleTV.
Allerdings können hierbei wie zuvor beschrieben nur von Apple zertifizierte Geräte oder Sensoren in die Umgebung eingebunden werden. Für die Zertifizierung der Geräte verlangt Apple dabei ein Entgelt.
Eigene Smart Home Geräte bzw. Sensoren wie Lichtsteuerungen oder Temperatursensooren werden von Apple bisher nicht angeboten, sondern lediglich die in Ihrer Platform als Steuerungszentrale fungierende Geräte wie Iphones, AppleTVs oder Ipads.

Das Geschäftsmodell von Apple bzgl. Smart Home basiert hauptsächlich auf dem Vertrieb von Zertifizierungen welche für andere Smart Home Hersteller angeboten werden. Das Angebot ihrer Platform HomeKit und die flächendeckende Verbreitung von Apple-Geräten wie dem Iphone hat den netten (kalkulierbaren) Nebeneffekt, dass die als Steuerungszentrale fungierenden Applegeräte stark in den Smart Home Bereich eingewoben werden.
D.h. durch die enstehende Abhängigkeit zu Apple könnten ebenfalls die Absätze der anderen von Apple angebotenen Produkte positiv beeinflusst werden.

#### Produktpalette
Die Produktpalette für Smart Home von Apple ist nicht ganz trivial einzuordnen.
Iphones, Ipads und andere Appleprodukte dienen lediglich zur Steuerung und zum Abruf von Informationen. Smart Home Geräte wie smarte Glühbirnen oder Temperatursensoren werden dabei allerdings nicht direkt von Apple vertrieben.
Hierbei haben sich allerdings im Laufe der Jahre eine ganze Reihe an Herstellern mit ihren Produkten angepasst und die von Apple bereitgestellte Schnittstelle für Ihre Geräte und Sensoren implementiert. Um die Funktionalitäten nutzen zu können vertreibt Apple entsprechende Zertifizierungen für ihre HomeKit-Umgebung.

Zertifizierte Produkte und Hersteller sind mittlerweile reichlich vorhanden, hier ein paar kleine Beispiele:

* (Sicherheits-)Kameras von bspw. Logitech oder D-Link
* Bewegungssensoren von bspw Elgato
* smarte Glühbirnen von bspw. Philips
* Termostate von bspw. Honeywell
* ...

(Demnächst wird Apple ihr Angebot durch einen smarten Lautsprecher erweitern, welcher zudem als Homeassistant dienen soll um sein smartes Zuhause bequem per Spracheingabe steuern zu können.)

#### Protokolle
Das von Apple HomeKit verwendete Protokoll zur Kommunikation innerhalb der Smart Home Landschaft wird als 'Homekit Accessory Protocol" (kurz HAP) bezeichnet. Der von Apple entwickelte Standard kommuniziert über WLAN und Bluetooth 4.0 mit den Smart Home Geräten und Sensoren.
Protokolle wie Zigbee oder Z-Wave sind aktuell nicht direkt mit HomeKit steuerbar, da die steuernden Geräte (bspw. Iphone) diese Protokolle nicht unterstützen.

#### Kompatibilitaet
Da das Apple HomeKit aktuell nur das HomeKit Accessory Protocol unterstützt, können nur Geräte mit entsprechender Zertifizierung in das Smart Home integriert werden. Umwege können ggf. anhand sogenannter Bridges realisiert werden, wenn der anbietende Hersteller  über entsprechende Geräte inklusive benötigter HomeKit-Zertifizierungen verfügt. Da Apple jedoch selbst entscheidet wen und was sie zertifizieren und was nicht, ist eine solche Kombination aus bspw. Zigbee und HAP nicht unbedingt realisierbar bzw. nur nach der Freigabe von Apple.
Bisher ist nicht ersichtlich, ob Apple Bridges dieser Art für ihre Umgebung zertifizieren wird, oder ob sich die Zertifizierung nur auf Endgeräte wie Sensoren oder Ähnliches beschränkt.

### Bosch bzw. BSH
#### Platform
Mit seiner Bosch IoT Suite hat BSH eine offene IoT Plattform für alle Anwendungsbereiche geschafft. Die Plattform ist as a Service angeboten und gilt als eine Schicht in der breiteren Bosch IoT Cloud Architektur. Die dient dazu Dinge mit dem Internet zu verbinden. Zugeschnitten für die üblichen Anforderungen an IoT Use-Cases und ergänzt durch weitere Dienste wie Datenbanken, Laufzeit und E-Mail bietet Bosch IoT Suite Entwicklern die notwendigen Werkzeuge an, um schnell robuste und skalierbare IoT-Anwendungen zu realisieren. Die Cloud-Services-Palette stellt Lösungen für eine zuverlässige Verwaltung von Geräten, Maschinen und Gateways, für eine Sichere Zugriffsverwaltung, für das Ausführen von Software-Rollout-Prozessen, für die Integration von Drittsystemen und –Diensten sowie für die Datenanalyse zur Verfügung. Außerdem setzt die Plattform auf offene Standards und Open Source als flexible Lösung zur Unterstützung der Integration anderen Plattformen. Vertrauenswürdigkeit und Sicherheit bezeichnen zudem die BSH IoT Suite, denn BSH setzt auch auf höchsten Privacy-Standards und modernen Sicherheitsmechanismen.
Heute sind Millionen von Geräten und Maschinen bereits über diese Plattform angebunden und die Diensten der Plattform auf dem Marktplatz der Bosch IoT Cloud zugänglich. Bosch plant diese Dienste künftig auf anderen Marktplätzen zu veröffentlichen, die auf Cloud Foundry basieren.     

![bsh_iot_suite.png](https://github.com/cdeck3r/SmartLab/blob/master/bsh_iot_suite.PNG)      
Abbildung 1: Bosch IoT Suite Architektur

#### Geschaeftsmodell
In seiner Vision der Zukunft sieht BSH alle seine elektronischen Geräte mit einer Internetfähigkeit ausgestattet, da erst die Vernetzung zwischen diesen die Tür zu weiteren Geschäftsmodellen und innovativen Services führt. In diesem Wandel, wo physische Objekte(Dingen) und logische Service zu Koordinierten Systemen werden beobachtet BSH hier eine neue Art der Wertschöpfung in einem Netz von mehreren Beteiligten, mit multidirektionalen Leistungen und Wertströmen. Für BSH ist der Nutzen von Partnern von Anfang an explizit zu machen, da die Partnerschaft dabei eine große Rolle Spielt. Mit seiner Bosch IoT Suite und Bosch IoT Cloud sorgt BSH für eine gute Konnektivität zwischen Anwendern, Geschäftspartnern, Maschinen, Geräten und Unternehmenssystemen. Der Einsatz von offenen Standards ermöglicht es. Diese Konnektivität ist das wichtigste Erfolgsfaktor für wertschöpfende IoT-Lösungen in verschiedenen Bereichen wie Smart Home, Smart City, Mobilität, Energie, Industrie 4.0 etc… Mit der Bereitstellung von so einer offenen und robusten Plattform können sich Anwender auf wertschöpfende IoT-Lösungen anstatt auf Entwicklung, Hosting, und Wartung der IoT-Plattform konzentrieren. Der Anwender maximiert dabei sein ROI und Bosch macht Einnahmen von der Nutzung seiner Software-Services. Die Abrechnung für die Nutzung der Services wird für den Anwender über sogenannte Service-Pläne, stufenweise nach Anzahl angebundener Geräte, aktiver User, Transaktionen oder Datenvolumen transparent ermittelt. Für eine Nutzung, die über diese Kriterien hinausgeht wird eine Abrechnung über ein sog. Usage-based Preismodell durchgeführt. Für jeder Bosch IoT Suite Service stehen sogenannte Freemium-Pakete für Testphase oder für die Entwicklung von Prototypen zur Verfügung. Um Geschäftsrisiken seiner Kunden zu minimieren unterstützt Bosch IoT Suite innovative Geschäftsmodelle mit dem sogenannten Revenue Sharing.   

#### Produktpalette
Die IoT-Produktpalette von Bosch zu nennen kann schwierig sein, da Bosch als Hersteller von Elektrogeräten stattet sie heutzutage immer mehr mit einer Internetfähigkeit aus. Von Kühlschränken über Kaffeemaschinen bis hin zu Rauchmeldern kann man sehr viele internetfähige Bosch-Geräte auflisten, was hier nicht sinnvoll wäre. Allerdings kann man IoT-Bereiche nennen bei denen Bosch einen erheblichen Auftritt aufweist.
* **Bosch Smart Home**: in diesem Bereich stellt BSH eine Vielfalt von internetfähigen Produkten bereit, die zusammen in Paketen aufgestellt und verkauft werden können oder einzeln verkauft werden können.
     * **Raumklima Starter-Paket**
     * **Heizung Starter-Paket**
     * **Sicherheit Starter-Paket** 
     
     Diese Pakete beinhalten je nach dem null, einen Stück oder mehrere Stücke dieser Komponenten: Bewegungsmelder, Rauchmelder,              Tür-/Fensterkontakt, Heizkörper-Thermostat. Alle Pakete beinhalten aber obligatorisch einen Smart Home Controller und  und eine          App. Für die Steuerung. Weitere Produkte wie Kameras, Schalter, Zwischenstecker… gehören auch zu dem Bosch Smart Home                    Produktpalette.

* **Industrie 4.0**: hierbei hilft Bosch IoT Lösungen Produktions- und Logistikprozesse zu optimieren. Dieser Bereich geht auch über unseren Projektumfang hinaus.
* **Etc**…

#### Protokolle und Kompatibilität
Laut BSH ist die große Menge der zu vernetzten Geräte bei der Umsetzung des Internet der Dinge seiner Erfahrung nach nicht die größte Herausforderung, sondern der zuverlässige Umgang mit den ständig diversen und heterogenen entwickelten Geräten und Maschinen. Dafür bietet BSH eine Softwarelösung, ein Gateway- und Gerätemanagementsoftware, welche die Interaktion zwischen vernetzten Geräten erleichtert. Die Strategie liegt in dem Einsatz von offenen Standards und Open-Source-Technologien wie Linux, Java, Eclipse IDE, OSGi und Cloud Foundary. Übrigens ist der OSGi Standard für die Realisierung der Gateway-Technologie (ProSys Gateway Software) und die technische Fernwartung grundlegend, sodass alle wichtigen IoT-Protokolle für die Integration von Geräten und Sensoren unterstützt werden.
Die ProSyst Gateway Software unterstützt unter anderen folgende Protokolle:
* Z-Wave
* ZigBee
* DECT
* BLE
* wMBUS
* EnOcean
* UPnP
* KNX
* X10
* HomeMatic
* Webcams etc.    

und ermöglicht Fernzugriffe über Java, JCA, JMS, SOAP, REST, JSON-RPC.     

#### Sicherheit
Von BSH werden zu Gunsten der Anwender Datenschutz-Standards streng eingehalten. Einen Überblick der von BSH generierten daten werden Kunden zur Verfügung gestellt sowie die Möglichkeit zu entscheiden, was mit den Daten zu tun ist. Die Daten, die in der Bosch IoT Cloud Deutschland gehostet sind unterliegen dazu dem deutschen Bundesdatenschutzgesetz.
Was die Sicherheit in der Systemarchitektur der Bosch IoT Cloud geht kümmert sich speziell seine Tochtergesellschaft ESCRYPT darum. Die Anbindung von Dingen (Sensoren, Geräte, etc), von Beteiligten(Akteure, Partner, etc) sowie von weiteren eingebetteten Systemen ist mit einer Public Key Infrastruktur abgesichert, um Sicherheits- und Privacy-Anforderungen zu befriedigen.

### Google
#### Platform
Google hat im 2016 sein eigenes Home-System dargestellt. Das System arbeitet auf Basis von Google Assistenten. Es verbindet sich automatisch über WLAN mit dem Google-Konto. Alle Angebote von Chromecast werden auf dem Home-System laufen nach Angaben von Google. 
Google Home stellt dem Nutzer Funktionen des hauseigenen intelligenten persönlichen Assistenten Google Assistant zur Verfügung. Durch das Sprachbefehlen kann man Fragen oder Befehle an Google Home übermitteln, die das Gerät versucht zu beantworten oder umzusetzen. Drittanbieter können Über die Entwicklerplattform „Actions on Google“ Dienste in Google Home integrieren.

#### Geschaeftsmodell
Die Home-Systeme ergeben sich aus bewährten und bekannten technischen Systemen. Um die Geschäftsidee einer digitalen Heim-Plattform erfolgreich umsetzen zu können, nutzen sie gelernte Geschäftsmodelle. Die grundlegendsten Prozesse laufen auf den Servern der Unternehmen ab.  Anfragen an das Home-System werden über die Server geschleust. Zur Folge entsteht ein weiterer Layer zwischen Endkunde und Drittanbietern. Diese Anbieter haben einen Zugriff zum System und zusätzlich können Smart-home-Geräte, Fahrzeuge und andere Alltagsgegenstände mit dem System vernetzt werden.
#### Produktpalette
![Google_Produktpalette.PNG](https://github.com/cdeck3r/SmartLab/blob/master/Google_Produktpalette.PNG)  
Abbildung 2:Google Produktpalette
#### Protokolle
Google Home hat eine Smart Home-Anbindung und das kann mit eigenen vernetzte Heimnetzwerk eingebunden werden. Bisher wissen wir nicht, ob Google Standard Protokolle wie ZigBee oder Z-Wave unterstützt. Bestimmt dürfte sein, dass Google sein eigenes Protokoll Weave unterstützen wird, zu guter Letzt unterstützen die Nest-Produkte auch das Protokoll. Google hat versprochen, dass es später eine API geben soll.

#### Kompatibilitaet
Google Home hat Ende 2016 den Dienst einigen Drittanbietern freigegeben. Die wichtigsten Vertreter, die mit Google Home kompatibel sind : Honeywell Evohome, Nest, Philips Hue, Samsung Smart-Things sowie Belkin WeMo, TP-Link Kasa, mydlink home und Osram Lightify. 
Eine Lösung, um den Funktionsumfang von Google Home noch weiter zu verbessern, ist der unterstützte Webservice IFTTT, mit dem man Geräte in das System einbindet, die noch nicht von Google Home direkt erkannt werden, beispielsweise die Einbindung des Logitech Harmony Hub. 


### Quivicon
#### Platform
#### Geschaeftsmodell
#### Produktpalette
#### Protokolle
#### Sicherheit
#### Kompatibilitaet

### eQ-3
#### Platform
Die eQ-3 AG ist ein deutsches Technologieunternehmen im Bereich Smart Home. eQ-3 vertreibt seine Produkte unter der Marke HomeMatic, Homematic IP und MAX!. Darüber hinaus ist das Unternehmen Auftragsfertiger für innogy und QIVICON, eine Smart Home-Allianz der Deutschen Telekom.

HomeMatic

Unter der Baureihe Homematic, vertreibt eQ-3 Produkte, welche die Steuerung von einfachen Funktionen bis hin zu komplexen Szenarien in Haus oder Wohnung ermöglichen. Haupt-Augenmerk liegen neben der Steigerung von Komfort und Sicherheit in der Reduktion von Energiekosten. Kernbestandteil einer Homematic-Installation bildet die Zentrale Steuereinheit CCU2. 

Homematic IP

Homematic IP basiert in Teilen auf der Produktreihe Homematic und umfasst Produkte aus den Bereichen Raumklima, Sicherheit und Licht. Im Gegensatz zur Produktreihe Homematic liegt der Fokus hier auf der Steuerung über die ebenfalls von eQ-3 entwickelte Smartphone-App. Konfiguration und Bedienung laufen über den von eQ-3 betriebenen Homematic IP Cloud-Service. Die in der Homematic IP Cloud gespeicherten Daten liegen dort in anonymisierter Form vor, da von Seiten des Nutzers keine Daten im Rahmen der Registrierung abgefragt werden. Über die Homematic Zentrale CCU2 können Homematic IP Geräte in ein bestehendes Homematic System eingebunden werden.

MAX!

Die Produktreihe Max konzentriert sich auf die Steuerung von Temperatur in Wohnräumen entweder als lokale Heizkörperregelung oder als zentral gesteuerte Lösung. Zur Realisierung kommen Heizkörperthermostate, Fensterkontakte, Wandthermostate sowie die von eQ-3 entwickelte Smartphone-App zum Einsatz.


#### Geschaeftsmodell
Alle Produkte aus dem Hause eQ-3 wird fein säuberlich gegeneinander abgeschottet. HomeMatic setzt auf den autarken, absolut datenschutzkonformen Eigenbetrieb hinter dem eigenen DSL-Anschluss. Cloud-Dienste müssen kostenpflichtig hinzugebucht werden. Steuerung der hauseigenen Produkte nur über die eQ-3 SmartHome App. 

#### Produktpalette
*	HomeMatic Zentrale CCU2
*	Heizkörperthermostate
*	Rollladen-und Markisensteuerung
*	Sicherheit und Überwachungsgeräte
*	KeyMatic für Haustüren usw.

#### Protokolle
* HomeMatic: Datenübertragung wird das ISM-Band Typ B im Bereich 868-MHz genutzt
* Homematic IP: Als Datenübertragung wird IPv6 sowie das ISM-Band Typ B im Bereich 868-MHz genutzt

#### Kompatibilitaet
Homematic Zentrale CCU2 übernimmt als zentrales Element des Homematic Systems vielfältige Steuer-, Kontroll- und Konfigurationsmöglichkeiten für alle Homematic Geräte einer Installation. Bedienung der Zentrale erfolgt komfortabel am PC oder Tablet über die Homematic Bedienoberfläche WebUI. Konfiguration und Bedienung sowie Statusabfrage aller Homematic Geräte möglich und Komplexe Steueraufgaben sowie individuelle Logikfunktionen können über Zentralenprogramme realisiert werden

### Afriso
#### Platform
AFRISO Smart Home bietet umfassende Lösungen, die mit vielen Produkten anderer Hersteller kommunizieren bzw. zusammenarbeiten können.
Die Idee von AFRISO für den zuverlässigen Betrieb von Smart Home Lösungen ist ein Gateway zu bilden, das in Zukunft herstellerunabhängig arbeiten wird. Wer eine EnOcean-Signalübertragung nutzt, vermeidet lästiges Batterie-Austauschen, aufwändiges Kabel-Verlegen und hat dank „Plug & Play“ Installation bei der Inbetriebnahme keinen Schlitze-Klopfen-Schmutz oder Bohrstaub im Gebäude. 

#### Geschaeftsmodell
Nutzer der AFRISOhome App haben weltweit und jederzeit ihr eigenes Heim im Überblick: Fenster sind offen oder geschlossen? Raumtemperatur? Alle Messwerte, Batteriestatus und Verbindungsqualität des Gerätes werden angezeigt.
Die App bietet die Gelegenheit ganz einfach, dass alle Geräte im Haus zu überwachen und zu steuern. Um man schnell einen Überblick über den Temperaturverlauf, Stromverbrauch oder andere Kennzahlen im Haus zu bekommen werden die Daten in übersichtlichen Diagrammen angezeigt. Durch regelmäßige Updates stehen beständig neue Funktionen und Geräte bereit. Das Anlernen von Sensoren ist einfach und wird Schritt für Schritt durchgeführt. Abläufe können durch das Erstellen von Programmen mit einfachen „Wenn-Und-Dann-Funktionen“ automatisiert werden.

#### Produktpalette
•	Füllstandmessgeräte
•	Abfüll-/ Überfüll- Sicherungen und Füllstandregler
•	Leckanzeigegeräte, Leck- Überwachungssysteme, Tankinnenhüllen
•	Warn- und Signalgeräte 
•	Smart Building System
•	Sicherheit rund um den Tank
•	Sicherheit rund um die Heizung
•	Armaturen und hydraulischer Abgleich
•	Wassertechnik
•	Servicemessgeräte.

#### Protokolle
Alle gängigen Geräte, die per EnOcean, Z-Wave, Zigbee, M-Bus oder W-LAN übertragen, kommunizieren mit dem AFRISOhome Gateway, deswegen kann man neben den AFRISO Sensoren und Aktoren schon heute viele weitere Geräte im Zuhause steuern und sind auch in Zukunft herstellerunabhängig.
#### Sicherheit
Die Datensicherheit ist hoch, weil alle Daten sind auf dem eigenen Gateway und werden wie beim Onlinebanking nur verschlüsselt übertragen. 
Das System verzichtet komplett auf eine Cloud-Lösung. Somit kann es auch ohne Internetverbindung betrieben werden.


#### Kompatibilitaet
Das AFRISOhome Gateway bildet die Basisstation für den zuverlässigen Betrieb von kleinen Smart Home Lösungen oder auch sehr komplexen Gebäudemanagementsystemen. Produkten anderer Hersteller, wie z. B. den Inspektionssystemen von Netatmo, den Lichtsteuerungen von Philips Hue und IKEA Tradfri oder der Sprachsteuerung Alexa von Amazon auf Basis von EnOcean, Z-Wave, Zigbee, M-Bus oder W-LAN können mit diesem Gateway auch zusammenarbeiten.

## Smart Shop Bereich
### Einleitung
Die Digitale Transformation ist mittlerweile in jeglichen Bereichen der Wirtschaft von großer Relevanz. Ebenso gilt dies selbstverständlich für den Retail- und Consumer-Bereich, welcher durch den Einsatz neuer technologischer Trends und den durchgeführten Adaptionen an den bestehenden Businessprozessen durch neue elektronische Möglichkeiten zunehmend digitalisiert wird.

Im Smart Shop bzw. Retail-/Consumer-Bereich sind bisher allerdings meist Neuerungen in Form von Technologien o.Ä. nicht für den Kunden sichtbar, sondern finden nur im Backend der Shops statt, wie beispielsweise in der Business Intelligence, der Logistik, sowie Personal- und Ladenverwaltung, sodass Kunden diese Art von digitaler Weiterentwicklung nicht direkt wahrnehmen können. Einfache Beispiele hierfür findet man in der Logistik von Retailunternehmen: Hier kommen oftmals u.a. z.B. Tracking-Systeme  zum Einsatz, welche neue Möglichkeiten bieten, komplette Lieferungsprozesse von Produkten oder bestimmter Produktbündel vom Hersteller bis zum Eintreffen im Einzelhandel nachverfolgen zu können und dabei zusätzliche Informationen für in Beziehung stehende Prozesse liefern kann.

Allerdings gelangen mittlerweile bereits auch einige für den Kunden sichtbare Technologien den Weg in den Retail-Bereich. Eine aktuell in den Medien präsente Anwendung neuer digitaler Technologien und Services und deren Integration in die Geschäftsprozesse, ist beispielsweise der von Amazon vor einigen Wochen für die Öffentlichkeit zugänglich gemachte ‚Amazon Go‘ Shop. Aber auch beispielsweise mittlerweile relativ häufig eingesetzt oder eher angeboten wird ein sogenannter Chatbot, welcher eine automatisierte Kundenberatung, meist auf einer Onlineplattform bereitgestellt, darstellt. Im Bereich der In-Store-Vermarktung können von Kunden bereits relativ häufig sogenannte Smart Screens entdeckt werden, welche das aktuelle Angebot oder Sonderrabatte dem Kunden bereitstellt.

Ein weiterer nicht unwichtiger Bereich, der für den Kunden erkennbaren Einsatz von neuen  Technologien oder durch neue Technologien entstandenen neuen Businessprozessen, könnten zukünftig zudem neue Bezahlverfahren werden. In diesem Bereich gibt es allerdings bisher noch keine breite Anwendung. Vorreiter stellt hier allerdings ebenfalls der zuvor erwähnte Amazon Go Shop dar, denn dort kann bereits heute kontaktlos bezahlt werden. Diese oder auch weitere ähnliche Anwendungen im Bezahlverfahren sind in naher Zukunft vermutlich unabdingbar, um Kunden nicht an ggf. technologisch weiter fortgeschrittene Konkurrenz zu verlieren.

### Technologien
#### Digitale Preisschilder
Während die meisten bisher im Einsatz befindlichen Technologien vom Kunden überwiegend unbemerkt bleiben, stellen digitale Preisschilder (auch ‚Electronic Shelfs‘ genannt), einen bedeutenden Kontaktpunkt zwischen Kunden und Händlern dar. ‚Elektronic Shelfs‘ stellen digitale Preisschilder dar, welche an die IT-Infrastruktur des anbietenden Einzelhändlers gekoppelt sind. Zudem stellen diese Schilder die Preise digital auf einem Display dar, wodurch zeitintensive Arbeiten wie neue Preisauszeichnungen nicht mehr manuell gesteckt, sondern zentral und automatisiert über die IT-Infrastruktur des Händlers gesteuert und somit immer aktuell gehalten werden können. Insbesondere beim Verkauf von Waren mit häufigen Preisschwankungen stellt diese Technologie ein hohes Einsparpotential dar, da in diesem Fall spürbar der Personalaufwand verringert wird.

‚Electronic Shelfs‘ bieten allerdings jede Menge weitere Potentiale der Optimierung. Beispiele hierfür sind die bereits angeschnittene Preisanpassung in Echtzeit, oder auch die Möglichkeit der Darstellung von Zusatzinformationen. Ebenfalls vorstellbar ist es, dass der Kunde zukünftig direkt mit dem Preisschild interagieren kann um bspw. weitere Informationen zu erhalten. Außerdem wird durch dessen Einsatz die Fehlerquote von falschen Preisauszeichnungen deutlich gesenkt.
Durch den Einsatz solcher Technologien sind aber auch komplett neue Preismodelle vorstellbar, bzw. die Dynamik von Preisen kann beliebig steigen. Dadurch könnte man bspw. Preise abhängig von der Uhrzeit anpassen. Eine gute Anwendung hierfür könnte MHD-Ware sein wie z.B. Obst und Gemüse, welches auf Basis des MHDs automatisiert reduziert werden kann, was i.d.R. gar nicht, oder manuell von Marktpersonal durchgeführt werden muss.

Zusammenfassend kann gesagt werden, dass diese Technologie nicht nur wesentliche Verbesserungen bestehender Prozesse bietet, sondern ebenfalls teils komplett neue Berührungspunkte mit dem Kunden schafft und somit völlig neue Prozesse generiert.
Die immens spürbaren Einsparpotentiale durch die Vernetzung der Filiale anhand digitaler Preisschilder machen einen Einsatz für Einzelhändler zu einem entscheidenden Wettbewerbsfaktor – d.h. wer dies und dessen Einsatz verpasst, wird sehr wahrscheinlich in Zukunft nicht mehr wettbewerbsfähig sein. 

#### Smart Bluetooth / Beacons
#### Smart Screens
#### Plattform
Ein Big Player im Bereich des Smart-Retails ist die Westfield Gruppe. Besonders in Australien, Neuseeland und Großbritanien ist die Gruppe einer der größten Betreiber von Einkaufszentren.
Zu ihrer Technologie-Stack hat das Unternehmen eine neue und mächtige Plattform gebracht: die sogenannten Smart-Screens. Durch Gesichterkennung, Bewegungsmuster und Daten aus dem Smartphone werden vorbeigehenden Passanten personalisierte Werbungen angezeigt.
Dank miteinander vernetzter Digitalbildschirme in einem Netzwerk kann mit dieser Technologie eine breite geographische Reichweite gedeckt werden. Somit können Westfield-Kunden überall erreicht und angesprochen werden. 175 miteinander verbundene digitale Screens bilden momentan ein Teil von über 1500 andere digitale Smart-Screens und Super-Screen, die positioniert sind, um jede Einkaufstour in 46 Einkaufszentren (39 Westfield Einkaufszentren) durch raffinierte Werbung zu beeinflussen. Super-Screens sind Leistungsstärker und Aussagekräftiger als Smart-Screens. Die sind durch Ihre Helligkeit und Größe Charakterisiert und sind mit Full-Motion-Video, Audioausgabe, Interaktivität, Augmented-Realität und Live-Broadcast-funktionen ausgestattet, was ein reiches Markenerlebnis anregen kann.     

Die Werbung wird nicht nur für Händler in Einkaufszentren gemacht. Der Service kann eigentlich jedem Interessenten zu Nutzen sein. Es werden zum Beispiel auch Inhalte für Immobilien, lokale Kunst oder Live-Events wie Konzerte usw. übertragen.
Hausmarken und andere Interessenten werden dann Qualität, geographische Deckung, Kontrolle sowie digitale Flexibilität zur Verfügung stehen, was ihre Marketing-Strategien unterstützen und ihre Ziele maximieren wird.

#### Geschäftsmodell
Westfield Gruppe Vision ist es, Mit über 46 Millionen Einkaufsbesuchen pro Jahr Marken und Werbepartner zu verbinden sowie Energie und Umsätze um über 800 Einzelhandelsdestinationen in ihren Zentren in Neuseeland zu steigen.
Einzelhändler, Werbungstreibende und andere Interessenten,  können Westfeld Gruppe Kunden und wahrscheinlich ihre eigene Kunden dank dieser Technologie erreichen und live ihnen passende Produkte oder Dienste anbieten. Außerdem werden sie die Möglichkeit haben Vorteile des gesamten Netzwerks zu haben oder nur von ausgewählten Subnetzwerken, die zu ihren Zielen passen. Zusätzlich dazu wird eine digitale Experience für Kunden geschafft.

Auf den Smart-Screens können bereits wichtige Marken wie Nespresso, Sky TV, Nivea, Gehalten, Fisher & Paykel und Vodafone als Startpartner von Westfield Smart-Screen Netwerk gesehen werden.

##### Geräte
Eine Liste einiger Komponenten kann  anhand der Funktionsweise des Services ermittelt werden:
*	(Touch-)Screens: Westfield produziert selber seine Screens, um Kontrolle über die Qualität zu gewährleisten.
*	Kameras
*	Bewegungssensoren

#### Amazon Go / Sensor Fusion
#### Plattform

Amazon Go, der neue Convenience-Store für Lebensmittel, Snacks und Getränke in Seattle (2131 Seventh Avenue) ist vermutlich weltweit der fortschrittlichste Supermarkt. Denn er kommt ohne klassische Kassen aus. Die Kunden checken per App und QR-Code-Scan im Laden ein, Sensoren – Amazon raunt im Video von „Sensor Fusion“ - erkennen, welche Produkte aus dem Regal genommen werden. Die Technik erlaubt, dass der Kunde den Laden dann einfach mit der Ware verlassen kann. Die Abrechnung erfolgt wiederum per App.
Amazon selbst begnügt sich mit nebulösen Schlagworten, spricht von “computer vision, sensor fusion and deep learning” und lässt lediglich durchblicken, dass man Technologien einsetze, wie man sie von selbstfahrenden Autos kennt.
Kameras und eine Kombination unterschiedlicher Sensoren füttern also einen lernenden Algorithmus, der erkennen soll, welche Produkte Kunden aus dem Regal nehmen – oder wieder zurückstellen.

Die Daten über den Nutzer spielen nämlich womöglich eine zentrale Rolle für die künstliche Intelligenz des lernenden Algorithmus und die Erkennung der Artikel.

Das muss man sich dann – grob vereinfacht - so vorstellen:

<li> Der Kunden greift sich eine rote Flasche aus dem Regal. </li>
<li> Die Kameras erkennen eine rote Flasche und registrieren, dass der Kunde das Produkt behält. </li>
<li> Die Kameras erkennen aber nicht genau das Produkt, weil ein anderer Kunde die Sicht verdeckt. </li>
<li> Der Kunde kauft regelmäßig Ketchup. </li>
<li> Der Ketchup steht normalweise genau an dieser Stelle. </li>
<li> Das System fügt Ketchup zum Warenkorb hinzu. </li>


#### Geschäftsmodell

Amazon-Pläne bis zu 2000 Ladeneröffnungen in den kommenden Jahren rund um Buchgeschäfte, Pick-up-Stores (der erste Laden ist am 22.01.2018 in Seattle schon geöffnet), und Amazon fresh-Shops, werden mit der neuen Technik zu einer weitaus gewaltigeren Bedrohung denn je. Amazon könnte die Expansion nämlich mit einem Bruchteil der Personalkosten wuppen.

Das Konzept eignet sich nicht nur für Lebensmittel, sondern lässt sich vermutlich auch auf andere Branchen wie Mode, Elektronik, Spielwaren, Kosmetik oder Bücher übertragen. 

##### Geräte

Damit man bei Amazon Go einkaufen kann, benötigt man : 
Die Amazon-Go-App für iPhone und Android auf dem Smartphone (um sich Zugang zum Laden zu verschaffen)

### Ausblick (?)
(vllt auf RFID oder andere mögliche Zukunftstechnologien eingehen)

## Trends
