// defines pins numbers

const int trigPin = 2;
const int echoPin = 5;
const int goalSize = 15;
const int ledPin = 4;

// defines variables
long duration;
int distance;
int counter = 0;
boolean wasScored = false;

void setup() {
pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin, INPUT); // Sets the echoPin as an Input
pinMode (ledPin, OUTPUT);
Serial.begin(115200); // Starts the serial communication
}

void loop() {
// Clears the trigPin
digitalWrite(trigPin, LOW);
delayMicroseconds(2);

// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);

// Reads the echoPin, returns the sound wave travel time in microseconds
duration = pulseIn(echoPin, HIGH);

// Calculating the distance
distance= duration*.034/2;

// If distance < goalSize someone scored
if (distance < goalSize) {
  counter=0;
  wasScored = true;
  Serial.println("Detecting that someone is scoring");
} else { turnOffLed(); }

  scored();

// Prints the distance on the Serial Monitor
Serial.print("Distance: ");
Serial.println(distance);
}

void scored() { 
  if (wasScored) {
     turnOnLed();

     counter++;
     if (counter>=50) {
    counter=0;
    wasScored=false;
  }  
}
}

void turnOffLed() { 
   digitalWrite (ledPin, LOW);
}

void turnOnLed() {
   digitalWrite (ledPin, HIGH);  
}
