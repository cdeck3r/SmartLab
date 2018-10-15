'use strict';

const Alexa = require('alexa-sdk');
const APP_ID = "amzn1.ask.skill.28c87888-1deb-406f-90a4-a2c7c809ee5d";
const HTTP = require('http');
const HTTPS = require('https');

const languageStrings = {
    'de': {
        translation: {
            
            FIRSTNAMES: [
                'Ivo',
                'Erik',
                'Gregor',
                'Michaela',
                'Cornelia'
            ],
            
            RESULT_MESSAGE: [
                'Heute ist %s dran!',
                'Ich sage: %s',
                'Ich sach mal: %s',
                'Ich will mal nicht so sein: %s',
                'Keine Widerrede: %s!',
                'Keine Diskussionen: %s!',
                'Ohne Wenn und Aber: %s!',
                'Spricht irgendwas gegen %s?',
                'Und der Gewinner ist: %s!',
                'Von mir aus: %s!',
                'Wie wär es mit %s?',
                '%s! Ohne viel Worte.',
                '%s! Das muss reichen.',
                '%s! Sage ich einfach mal.',
                '%s! Selber Schuld.',
                '<say-as interpret-as="interjection">abrakadabra</say-as>: %s!',
                '<say-as interpret-as="interjection">ähm</say-as><break time="50ms"/>%s!',
                '<say-as interpret-as="interjection">ohne scheiß</say-as>: %s!',
                '<say-as interpret-as="interjection">tja</say-as>: %s!',
                '%s! <say-as interpret-as="interjection">viel glück</say-as>',
                '%s! <say-as interpret-as="interjection">yay</say-as>',
                '%s! <say-as interpret-as="interjection">ach du grüne neune</say-as>',
                '%s! <say-as interpret-as="interjection">ach du liebe zeit</say-as>',
                '%s! <say-as interpret-as="interjection">ach du meine güte</say-as>',
                '%s! <say-as interpret-as="interjection">achje</say-as>',
                '%s! <say-as interpret-as="interjection">ah</say-as>',
                '%s! <say-as interpret-as="interjection">ahoi</say-as>',
                '%s! <say-as interpret-as="interjection">hach</say-as>',
                '%s! <say-as interpret-as="interjection">aber hallo</say-as>',
                '%s! <say-as interpret-as="interjection">hipp hipp hurra</say-as>',
                '%s! <say-as interpret-as="interjection">hurra</say-as>',
                '%s! <say-as interpret-as="interjection">hört hört</say-as>',
                '%s! <say-as interpret-as="interjection">jawahl</say-as>',
                '%s! <say-as interpret-as="interjection">jo</say-as>',
                '%s! <say-as interpret-as="interjection">kein kommentar</say-as>',
                '%s! <say-as interpret-as="interjection">keine ursache</say-as>',
                '%s! <say-as interpret-as="interjection">los</say-as>',
                '%s! <say-as interpret-as="interjection">prost</say-as>',
                '%s! <say-as interpret-as="interjection">tada</say-as>'
            ],
            
            FIRST_MESSAGE: 'Hallo! Ich habe noch keine Vornamen gespeichert. Bitte sage den ersten Vornamen.',
            FIRST_REPROMPT: 'Bitte sage einen Vornamen.',
            
            ADD_MESSAGE: 'Soll ich %s zur Liste hinzufügen?',
            ADD_REPROMPT: '%s hinzufügen?',
            ADD_AGAIN: '%s! Du kannst noch einen weiteren Vornamen hinzufügen, Beenden oder Liste sagen.',
            ADD_AGAIN_1: [
                'Ok',
                'Gebongt',
                'Mach ich',
                'Wird gemacht',
                'Okay',
                'Super',
                'Prima',
                'Klasse',
                'Schön'
            ],

            REMOVE_MESSAGE: 'Soll ich %s von der Liste streichen?',
            REMOVE_REPROMPT: '%s streichen?',
            REMOVE_AGAIN: '%s! Du kannst noch einen weiteren Vornamen streichen, Beenden oder Liste sagen.',
            REMOVE_AGAIN_1: [
                'Ok',
                'Gebongt',
                'Mach ich',
                'Wird gemacht',
                'Okay'
            ],

            AGAIN_NOT_OK: '%s! Ich habe den Vornamen wohl nicht verstanden. %s, oder sage Beenden oder Liste.',
            AGAIN_NOT_OK_1: [
                'Oh',
                'Oh je',
                'Ach Gott',
                'Zu dumm'
            ],
            AGAIN_NOT_OK_2: [
                'Sage ihn bitte noch mal',
                'Würdest Du ihn bitte wiederholen',
                'Könntest Du ihn noch mal sagen',
                'Könntest Du ihn wiederholen',
                'Probier es bitte noch mal',
                'Wiederhol ihn doch bitte',
                'Wiederhol ihn bitte',
                'Wiederhol ihn bitte noch mal',
                'Versuche es noch mal',
                'Versuchst Du es noch mal'
            ],
            AGAIN_REPROMPT: 'Noch ein Vorname, Beenden oder Liste?',            
            
            RESET_MESSAGE: 'Möchtest Du die Liste wirklich komplett löschen?',
            RESET_REPROMPT: 'Komplett löschen?',
            RESET_RESULT: 'Liste komplett gelöscht. %s',
            
            LIST_MESSAGE: 'Die Liste enthält %s Vornamen: %s. Du kannst Vornamen löschen, hinzufügen oder die komplette Liste löschen.',
            LIST_ADD: 'Die Liste enthält %s Vornamen: %s. Du kannst noch einen Vornamen hinzufügen, Beenden oder Liste sagen.',
            LIST_REMOVE: 'Die Liste enthält %s Vornamen: %s. Du kannst noch einen Vornamen entfernen, Beenden oder Liste sagen.',
            LIST_REPROMPT: 'Noch ein Vorname, Beenden oder Liste?',
            
            HELP_MESSAGE: 'Du kannst sagen, „Nenne mir einen Fakt über den Weltraum“, oder du kannst „Beenden“ sagen... Wie kann ich dir helfen?',
            HELP_REPROMPT: 'Wie kann ich dir helfen?',
            ERROR_MESSAGE: 'Ich habe den Vornamen nicht verstanden.',
            
            END_MESSAGE: [
                'Auf Wiedersehen!',
                'Danke!',
                'Bis demnächst!',
                'Bis zum nächsten Mal!',
                'Gerne wieder!',
                'Tschau!',
                'Ok!',
                'Bitte sehr!',
                'Tschüß!',
                '<say-as interpret-as="interjection">ade</say-as>',
                '<say-as interpret-as="interjection">alles klar</say-as>',
                '<say-as interpret-as="interjection">bis bald</say-as>',
                '<say-as interpret-as="interjection">bis dann</say-as>',
                '<say-as interpret-as="interjection">macht\'s gut</say-as>',
                '<say-as interpret-as="interjection">tschö</say-as>'
            ]
        },
    },
};

/*const SKILL_NAME = 'Space Facts';
const GET_FACT_MESSAGE = "Here's your fact: ";
const HELP_MESSAGE = 'You can say tell me a space fact, or, you can say exit... What can I help you with?';
const HELP_REPROMPT = 'What can I help you with?';
const STOP_MESSAGE = 'Goodbye!';
const PRODUCTS = ['Milch', 'Butter', 'Salami'];
//const FIRST_MESSAGE= 'Hallo ! ich habe noch keine Produkte in der der Einkaufsliste gespeichert. Bitte sage mir dein erstes Produkt';
const FIRST_REPROMPT = 'Bitte sage ein Produkt';
const ERROR_MESSAGE = 'Ich habe das Produkt nicht verstanden';
const ADD_MESSAGE = 'Soll ich %s zur Liste hinzufügen ?';
const ADD_REPROMPT = '%s hinzufügen ?';

const AGAIN_NOT_OK = '%s ! ich habe das Produkt wohl nicht verstanden. %s, oder sage Beenden oder Liste';
const AGAIN_NOT_OK_1 = [
        'Oh',
        'Oh je',
        'Ach Gott',
        'Zu dumm'
    ];
const AGAIN_NOT_OK_2 = [
        'Sage ihn bitte nochmal',
        'Würdest du ihn bitte wiederholen',
        'Könntest du ihn nochmal sagen',
        'Probier es bitte nochmal',
        'Wiederhol ihn doch bitte',
        'Versuche es nochmal',
        'Versuchst du es nochmal',
        'Wiederhol ihn bitte',
        'Wiederhol doch bitte nochmal'
    ];

const ADD_AGAIN = "Ok ! Du kannst noch ein weiteres Produkt hinzufügen, Beenden oder Liste sagen";

const REMOVE_MESSAGE = "Soll ich &s von der Liste streichen ?";
const REMOVE_REPROMPT = "&s streichen ?";
const REMOVE_AGAIN = "OK ! Du kannst noch ein weiteres Produkt streichen, Beenden oder Liste sagen";
const FIRST_MESSAGE = "Hallo, Wollen sie ein Produkt ihrem Generator hinzufügen ?";
//const FIRST_REPROMPT = "";

const AGAIN_REPROMPT = 'Noch ein Produkt, Beenden oder Liste?';*/


const MODES = {
    ADD: "MODE_ADD",
    REMOVE: "MODE_REMOVE",
    RESET: "MODE_REST",
    START: "MODE_START"
}


const handlers = {
    'LaunchRequest': function () {//-------------------------------------------------------------------------------------------------
    //------------------
        this.emit('GetStart'); // Start Intent ohne parameter und ohne aufforderung
    },
   
    'GeneratorIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
        this.emit('GetStart'); // Intent mit weiterer aufforderung
     },
     
    'GetStart': function(){
        
        this.attributes.mode = MODES.START;
        
        this.emit(':ask', 'Wollen Sie ihrer Einkaufsliste ein Produkt hinzufügen ?');
        
        
    },
    
     'AddIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
        //this.emit(':tell', 'Ich bin im AddIntent');
        
        this.attributes.mode = MODES.ADD;
        
        if(this.event.request.intent.slots.product)
        {
            const product = this.event.request.intent.slots.product.value.toLowerCase();
            this.attributes.product = product;
            this.emit(':ask', 'Wollen Sie ' + product + ' auf die Einkaufsliste setzen ?', 'Wollen sie ' + product + 'hinzufügen ?');
        }
        else
        {
            this.emit(':tell', 'ERROR_MESSAGE');
        }
    },
    
     'RemoveIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
        this.attributes.mode = MODES.REMOVE;
        
        if(this.event.request.intent.slots.product)
        {
            const product = this.event.request.intent.slots.product.value.toLowerCase();
            this.attributes.product = product;
            this.emit(':ask', 'Wollen Sie ' + product + ' von der Einkaufsliste entfernen ?', 'Wollen sie ' + product + 'entfernen ?');
        }
        else
        {
            this.emit(':tell', this.t('ERROR_MESSAGE'))
        }
    },
    
     'ListIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
         getResult(this.event.session.user.userId, '&command=list&product=list', result => {
            console.log(result);
            if(result == 'null')
            {
                this.emit(':ask', 'Deine Einkaufsliste ist leer ! Wollen sie ein Produkt ihrer Einkaufsliste hinzufügen oder Beenden ?')
            }
            else
            {
                this.emit(':ask', result + ', Du kannst Produkte löschen, hinzufügen, die komplette Liste löschen oder Beenden');
            }
        });
    },
    
     'EinkaufslisteResetIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
        this.attributes.mode = MODES.RESET;
        this.emit(':ask', 'Wollen sie Ihre Einkaufsliste leeren?'); 
     
    },
    
     'NameIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
        if(this.attributes.mode == MODES.ADD)
        {
            this.emit('AddIntent');
        } 
        else if(this.attributes.mode == MODES.REMOVE)
        {
            this.emit('RemoveIntent');
        }
        else
        {
            this.emit('AddIntent');
        }
        
    },
    
    'AMAZON.YesIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
    
        //this.emit(':tell', 'Ich bin im YesIntent');
        const product = this.attributes.product;
        
        if(this.attributes.mode == MODES.ADD)
        {
            const command = '&command=add&product=' + product;
            //this.emit(':tell', 'Ich bin im YesIntent:' + product);
            
            
            getResult(this.event.session.user.userId, command, result => 
            {
                console.log(result);
                if(result == 'Produkt ist schon in der Liste')
                {
                    //console.log("Läuft");
                    this.emit(':ask', product + 'ist schon auf der Liste, du depp ! Wollen sie ein anderes Produkt hinzufügen ?');
                }
                else if(result == "ok")
                {
                    this.emit(':ask', product + 'wurde der Liste hinzugefügt, Sie können ein weiteres Produkt hinzufügen , Liste abfragen oder Beenden.');
                }
            });
        }
        else if (this.attributes.mode == MODES.REMOVE)
        {
            const command = '&command=remove&product=' + product;
            
            getResult(this.event.session.user.userId, command, result => {
               this.emit(':ask', product + 'wurde aus der Liste entfernt, Sie können ein weiteres Produkt entfernen, Liste abfragen oder Beenden.'); 
            });
        }
        else if (this.attributes.mode == MODES.RESET)
        {
            getResult(this.event.session.user.userId, '&command=reset&product=list', result => {
                this.emit(':ask', 'Ihre Liste ist nun leer, Sie können ihrer Liste Produkte hinzufügen oder Beenden !'); 
             });
        }
        else if(this.attributes.mode == MODES.START)
        {
             this.emit(':ask', 'Welches Produkt wollen sie ihrer Einkaufsliste hinzufügen ?');
        }
    },
    
    'AMAZON.NoIntent': function () {//-------------------------------------------------------------------------------------------------
    //------------------
        //const text1 = randomItemFromArray(this.t('AGAIN_NOT_OK_1'));
        //const text2 = randomItemFromArray(this.t('AGAIN_NOT_OK_2'));
        if(this.attributes.mode == MODES.START)
        {
            this.emit(':ask', 'Wollen sie Ihre Liste abfragen, ein Produkt von der Liste entfernen, die Liste leeren oder Beenden ?');
        }
        this.attributes.mode == MODES.REMOVE
        {
             this.emit(':ask', 'Oh! Ich habe das Produkt wohl nicht verstanden. Bitte wiederhole es nochmal, oder sage Beenden oder Liste abfragen.');
        }
        this.attributes.mode == MODES.ADD
        {
             this.emit(':ask', 'Oh! Ich habe das Produkt wohl nicht verstanden. Bitte wiederhole es nochmal, oder sage Beenden oder Liste abfragen.');
        }
        //this.emit(':ask', this.t('Ich bin im No Intent'));
    },
    
    'AMAZON.HelpIntent': function() { //------------------------------------------------------------
    // ---------------
        const speechOutput = this.t('HELP_MESSAGE');
        const reprompt = this.t('HELP_MESSAGE');
        this.emit(':ask', speechOutput, reprompt);
    },
    
    'AMAZON.CancelIntent': function() { //----------------------------------------------------------
    // -----------------
        this.emit(':tell', 'Alles Klar ! DU kleiner Gauner !');
    },
    
    'AMAZON.StopIntent': function() { //------------------------------------------------------------
    // ---------------
        this.emit('AMAZON.CancelIntent');
    },

    'Unhandled': function() { //--------------------------------------------------------------------
    // -------
        this.emit('AMAZON.CancelIntent');
    },

    'SessionEndedRequest': function() { //----------------------------------------------------------
    // -----------------
        // do nothing. 1. Frage bleibt für 8 sec, Reprompt für weitere 8 sec. Wenn der Benutzer dann
        // immer noch nichts gesagt hat, wird diese Funktion aufgerufen.
    }
};

//=========================================================================================================================================

function randomItemFromArray(items)
{
    const index = Math.floor(Math.random() * items.length);
    return items[index];
}
//=========================================================================================================================================

function getResult(user, url, callback) {
    //   ---------
    var result = "";
    //var path = 'var/wwww/html/einkaufslistengenerator.php?user=' + user;
    var path = '/einkaufslistengenerator.php?user=' + user;
    //var path = '/einkaufslistengenerator.php?';
    
    if (url) {
        path += url;
    }
    
    const options = {
        host: 'alexa.itdhosting.de',
        path: path,
        method: 'GET'
    };
    
    
     HTTP.get(options, response => {
        response.setEncoding('utf8');
        var responseString = '';
        
        response.on('data', data => {
            responseString += data;
            console.log(responseString);
        });
        
        response.on('end', () => {
            const json = JSON.parse(responseString); // {'result': 'ivo'};
            console.log(callback(json.result));
            callback(json.result); // z.B. 'ivo', ['a', 'b', 'c']
        });
    });
    
    
    
    return result;
}

//=========================================================================================================================================

exports.handler = function (event, context, callback) {
    const alexa = Alexa.handler(event, context, callback);
    alexa.APP_ID = APP_ID;
    alexa.registerHandlers(handlers);
    alexa.execute();
};
