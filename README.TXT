Das Tutorial meint IMMER dieses: http://www.ralf-bauer.org/java/tutorial/Tutorial.zip, da wir nur dieses wirklich verwendet haben.
Der Mix aus Englisch und Deutsch im Code tut uns Leid, aber wir sind nunmal 5 Personen mit 5 verschiedenen Ideen und Wortschätzen.
Alle unbenutzen Methoden sind entweder alt und übersehen worden oder für zukünftige Funktionalitäten vorgesehen.

In den Klassen: Hauptmenue, Spielfeld, Spielanleitung, Endscreen wurde bei der Orientierung der Fenster,
die Fenster um -20 nach oben verschoben. Dies scheint kontraproduktiv, da davor die richtige Koordinaten berechnet wurden, damit das Fenster mittig auf dem Bildschirm erscheint.
Doch die Verschiebung ist nötig, da das Spielfeld-Fenster sonst teilweise hinter der Windows-Taskleiste verschwindet. Um die Fensteranordnung gleichmäßig zu halten,
werden auch die anderen Klassen (Hauptmenue, Spielanleitung, Endscreen) um -20 nach oben verschoben.

In den Klassen: Hauptmenue, Spielanleitung, Endscreen werden Fenster erzeugt die die Größe ihres Hintergrundbildes haben soll.
Die Hintergrundbilder sind immer 400px*400px groß. Dennoch muss die Größe der Fenster auf 406*429 geändert werden, da sonst nicht das ganze Hintergrundbild angezeigt wird.
Dies liegt daran, dass zu dem JFrame, auch noch der Rand dazu gezählt wird, auf welchem aber kein Bild angezeigt wird.
JFrames haben daher links und rechts eienen Rand von 3px und oben und unten insgesamt einen Rand von 29px.