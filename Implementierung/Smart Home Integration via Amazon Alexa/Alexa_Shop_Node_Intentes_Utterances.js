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
                        "und l�sche alle produkte",
                        "und leere die liste",
                        "und l�sche die liste",
                        "l�sche die liste",
                        "und l�sche die komplette liste",
                        "und l�sche alle eintr�ge",
                        "liste auf null",
                        "liste zur�cksetzen",
                        "komplette liste l�schen",
                        "liste l�schen",
                        "liste leeren",
                        "alle entfernen",
                        "zur�cksetzen",
                        "reset",
                        "alle l�schen"
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
                        "{product} hinzuf�gen",
                        "setze {product} auf einkaufsliste",
                        "setze {product} auf liste",
                        "und setze {product} auf einkaufsliste",
                        "und setze {product} auf die liste",
                        "und f�ge {product} auf einkaufsliste",
                        "und lade {product} ein",
                        "und f�ge {product} hinzu"
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
                        "{product} l�schen",
                        "entferne {product} von der einkaufsliste",
                        "und streiche {product} von der liste",
                        "und nimm {product} von der Liste",
                        "entferne {product}",
                        "und entferne {product}",
                        "l�sche {product}",
                        "und l�sche {product}"
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