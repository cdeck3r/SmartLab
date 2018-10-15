{
    "interactionModel": {
        "languageModel": {
            "invocationName": "shop note",
            "intents": [
                {
                    "name": "AMAZON.CancelIntent",
                    "samples": []
                },
                {
                    "name": "AMAZON.HelpIntent",
                    "samples": []
                },
                {
                    "name": "AMAZON.StopIntent",
                    "samples": []
                },
                {
                    "name": "AMAZON.NoIntent",
                    "samples": []
                },
                {
                    "name": "AMAZON.YesIntent",
                    "samples": []
                },
                {
                    "name": "EinkaufslisteResetIntent",
                    "slots": [],
                    "samples": [
                        "ich war einkaufen",
                        "und lösche alle produkte",
                        "und leere die liste",
                        "und lösche die liste",
                        "lösche die liste",
                        "und lösche die komplette liste",
                        "und lösche alle einträge",
                        "liste auf null",
                        "liste zurücksetzen",
                        "komplette liste löschen",
                        "liste löschen",
                        "liste leeren",
                        "alle entfernen",
                        "zurücksetzen",
                        "reset",
                        "alle löschen"
                    ]
                },
                {
                    "name": "ListIntent",
                    "slots": [],
                    "samples": [
                        "Liste abfragen",
                        "was ist auf der liste",
                        "und gib mir die liste",
                        "gib mir die produkte",
                        "gib mir die liste",
                        "liste",
                        "und nenne die liste",
                        "und liste die produkte",
                        "und sage die produkte"
                    ]
                },
                {
                    "name": "AddIntent",
                    "slots": [
                        {
                            "name": "product",
                            "type": "AMAZON.Food"
                        }
                    ],
                    "samples": [
                        "gib mir alle",
                        "{product} hinzufügen",
                        "setze {product} auf einkaufsliste",
                        "setze {product} auf liste",
                        "und setze {product} auf einkaufsliste",
                        "und setze {product} auf die liste",
                        "und füge {product} auf einkaufsliste",
                        "und lade {product} ein",
                        "und füge {product} hinzu"
                    ]
                },
                {
                    "name": "RemoveIntent",
                    "slots": [
                        {
                            "name": "product",
                            "type": "AMAZON.Food"
                        }
                    ],
                    "samples": [
                        "ich habe  {product} eingekauft",
                        "{product} entfernen",
                        "{product} löschen",
                        "entferne {product} von der einkaufsliste",
                        "und streiche {product} von der liste",
                        "und nimm {product} von der Liste",
                        "entferne {product}",
                        "und entferne {product}",
                        "lösche {product}",
                        "und lösche {product}"
                    ]
                },
                {
                    "name": "NameIntent",
                    "slots": [
                        {
                            "name": "product",
                            "type": "AMAZON.Food"
                        }
                    ],
                    "samples": [
                        "{product}"
                    ]
                },
                {
                    "name": "GeneratorIntent",
                    "slots": [],
                    "samples": [
                        "hallo"
                    ]
                }
            ],
            "types": []
        }
    }
}