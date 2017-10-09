const int buttonPin = 2;
volatile int pressed = 0;
long timer = 0;
const long INTERVAL = 50;
unsigned long lastDebounceTime = 0;

int buttonState;
int lastButtonState = HIGH;


void buttonPressed() {
  int reading = digitalRead(buttonPin);
  /*
  
  
    if (reading != lastButtonState) {
    lastDebounceTime = millis();
  }
   */
  
  if (millis() > timer){
    if (reading != buttonState){
            buttonState = reading;

            if (buttonState == LOW) {
              Serial.println("pressed");
              timer = millis() + INTERVAL;
            }
    }
  }

  lastButtonState = reading;
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);



  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, CHANGE);
  Serial.begin(9600);
}

void loop() {
  delay(1000);
  Serial.println("1 second");
  }

