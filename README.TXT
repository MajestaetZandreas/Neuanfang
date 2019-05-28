Das Tutorial meint IMMER dieses: http://www.ralf-bauer.org/java/tutorial/Tutorial.zip, da wir nur dieses wirklich verwendet haben.
Der Mix aus Englisch und Deutsch im Code tut uns Leid, aber wir sind nunmal 5 Personen mit 5 verschiedenen Ideen und Wortschätzen.
Alle unbenutzen Methoden sind entweder alt und übersehen worden oder für zukünftige Funktionalitäten vorgesehen.

In den Klassen: Hauptmenue, Spielfeld, Spielanleitung, Endscreen wurde bei der Orientierung der Fenster,
die Fenster um -20 nach oben verschoben. Dies scheint kontraproduktiv, da davor die richtige Koordinaten berechnet wurden, damit das Fenster mittig auf dem Bildschirm erscheint.
Doch die Verschiebung ist nötig, da das Spielfeld-Fenster sonst teilweise hinter der Windows-Taskleiste verschwindet. Um die Fensteranordnung gleichmäßig zu halten,
werden auch die anderen Klassen (Hauptmenue, Spielanleitung, Endscreen) um -20 nach oben verschoben. 