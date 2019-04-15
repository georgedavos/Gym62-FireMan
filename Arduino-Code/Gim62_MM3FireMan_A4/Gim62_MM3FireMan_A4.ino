/*
/*
62o Γυμνάσιο Aθηνών
Εργαστήριο Ρομποτικής 2015 - 2019

Ο παρόν κώδικας γράφτηκε με σκοπό την εκπαίδευση μαθητών και δεν ενδείκνυται για άλλη χρήση.

  
Διδάσκοντες Τάξης.
Δάβος Γεώργιος ( Καθηγητής Εργαστηρίου )
Σπύρος Μπερερης ( Μέθοδοι και χρήση ARDUINO )
Αθανάσιος Βλαστός ( Πρακτική χρήση και θεωρία των ηλεκτρονικών συστημάτων ). 

Μαθητές πού λαμβάνουν μέρος στην ανάπτηξη του Project.
Βενέτης Αναστάσιος Α1(9ο Λύκειο)
Μαλανκά Αλεξάνδρα Γ3
Παναγιωτάκης Ευάγγελος Γ3
Σπυροπούλου Ζωή Γ4

Συνδέσεις
Gym62-MM3 Shield - Κύκλωμα σχεδιασμένο αποκλειστικά για εκπαιδευτική χρήση απο το 62ο Γυμνάσιο Αθηνών.

Ενσωματώνει

1. Οδηγό για δύο μοτέρ τύπου Brashed. Μέγιστο ρεύμα μοτέρ 3Α στα 12V dc.
2. Διεπαφή σύνδεσης με Grid Thermal array.
3. Ενσωματωμένο θερμόμετρο LM35
4. Ενσωματωμένο LDR. ( Φώτο-αντίσταση )
5. Δέχεται δύο αισθητήρες απόστασης αντιδιαμετρικά. HC-SR04
6. Ενσωματωμένο WiFi Router With Server
7. Δέχεται Bluetooth Module.

Wifi SSID-Gym62MM3Robot
Connection Password 12345678
Web Service UserName : admin
Web Service Pass : admin

Blue-Tooth password : 123456789
RobotCam Password 192.168.11.101 /99
Version A4_1-15/4/2019

Περιγραφή τρόπου πλοήγησης.

Το σύστημα βασίζεται σε ένα αισθητήρα παράταξης υπέρυθρων ακτίνων.
Ο αισθητήρας αντιπαράταξης δομημένος σε πίνακα 8*8, δίνοντας 64 ανεξάρτητα σημεία μέτρησης της θερμότητας.
Χωρίζοντας τον αισθητήρα σε 8 ζώνες ορίζοντα, ό παρών κώδικας ανιχνεύει τον ορίζοντα στον οποίο εμφανίζεται η μέγιστη θερμοκρασία
Με βάση τον επιλεγμένο ορίζοντα, το όχημα μεταβάλει την ταχύτητα των ερπυστριών συνεχώς, αναλογικά σε σχέση πάντα με την θέση της μέγιστης θερμοκρασίας στον επιλεγμένο ορίζοντα.
Αυτή ή συνεχώς μεταβαλλόμενη ταχύτητα των ερπυστριών οδηγεί με μεγάλη ακρίβεια το όχημα στην θέση της πυρκαϊάς.   

*/
#include <Wire.h> // Essensial Library for I2C comunication

// Ορισμοί κίνησης Μοτέρ
#define FORWARD 0 // Ο μεταγλωττιστής όπου βρίσκει την λέξη FORWARD την αντικαθηστά με το 0
#define REVERSE 1 // Ο μεταγλωττιστής όπου βρίσκει την λέξη REVERSE την αντικαθηστά με το 1
#define LEFT 1    // Ο μεταγλωττιστής όπου βρίσκει την λέξη LEFT την αντικαθηστά με το 1
#define RIGHT 0   // Ο μεταγλωττιστής όπου βρίσκει την λέξη RIGHT την αντικαθηστά με το 0
#define ON 1      // Ο μεταγλωττιστής όπου βρίσκει την λέξη ON την αντικαθηστά με το 1
#define OFF 0     // Ο μεταγλωττιστής όπου βρίσκει την λέξη OFF την αντικαθηστά με το 0

#define PWM_L  3  // Ο μεταγλωττιστής όπου βρίσκει την λέξη PWM_L την αντικαθηστά με το 3 πού είναι το pin ~3 στο Arduino
                  // Το pin 3 παράγει αναλογική έξοδο τύπου PWM 0-255 και ρυθμήζει την ταχήτητα του Αριστερού Μοτέρ. ( Left ) 

#define PWM_R  9  // Ο μεταγλωττιστής όπου βρίσκει την λέξη PWM_R την αντικαθηστά με το 9 πού είναι το pin ~9 στο Arduino
                  // Το pin 9 παράγει αναλογική έξοδο τύπου PWM 0-255 και ρυθμήζει την ταχήτητα του Δεξιού Μοτέρ. ( Right ) 
                  
#define DIR_L  4  // Ο μεταγλωττιστής όπου βρίσκει την λέξη DIR_L την αντικαθηστά με το 4 πού είναι το pin 4 στο Arduino
                  // Το pin 4 παράγει ψηφιακή έξοδο τύπου 0 ή 1 και ρυθμήζει την κατεύθηνση του Αριστερού Μοτέρ. ( Left ) 
                  

#define DIR_R  8  // Ο μεταγλωττιστής όπου βρίσκει την λέξη DIR_R την αντικαθηστά με το 8 πού είναι το pin 8 στο Arduino
                  // Το pin 8 παράγει ψηφιακή έξοδο τύπου 0 ή 1 και ρυθμήζει την κατεύθηνση του Δεξιού Μοτέρ. ( Right ) 
                   
// Ορισμοί υπερηχητικών αισθητήρων απόστασης  
#define UltraTrigR 6  // Ο μεταγλωττιστής όπου βρίσκει την λέξη UltraTrigR την αντικαθηστά με το 6 πού είναι το pin 6 στο Arduino
                      // Το pin 6 παράγει ψηφιακή έξοδο τύπου 0 ή 1 και ενεργοποιεί τον πίσω αισθητήρα ( Triger - Rear ) 

#define UltraEchoR 7 // Ο μεταγλωττιστής όπου βρίσκει την λέξη UltraEchoR την αντικαθηστά με το 7 πού είναι το pin 7 στο Arduino
                      // Το pin 7 δέχεται ψηφιακή είσοδο τύπου 0 ή 1 και για την μέτρηση της απόστασης απο των πίσω αισθητήρα ( Echo - Rear ) 
                      
#define UltraTrigF 12 // Ο μεταγλωττιστής όπου βρίσκει την λέξη UltraTrigF την αντικαθηστά με το 12 πού είναι το pin 12 στο Arduino
                      // Το pin 12 παράγει ψηφιακή έξοδο τύπου 0 ή 1 και ενεργοποιεί τον μπροστινό αισθητήρα ( Triger - Front ) 
                      
#define UltraEchoF 13 // Ο μεταγλωττιστής όπου βρίσκει την λέξη UltraEchoF την αντικαθηστά με το 13 πού είναι το pin 13 στο Arduino
                      // Το pin 13 δέχεται ψηφιακή είσοδο τύπου 0 ή 1 και για την μέτρηση της απόστασης απο των μπροστινό αισθητήρα ( Echo - Front ) 

// Άλλοι Ορισμοί 
#define Lm35Pin  0 // Ο μεταγλωττιστής όπου βρίσκει την λέξη Lm35Pin την αντικαθηστά με το 0 πού είναι το pin A0 στο Arduino
                  // Το pin A0 Διαβάζει αναλογική είσοδο τύπου 0-255 ανάλογα με την θερμοκρασία του περιβάλοντος 

#define LdrPin  1 // Ο μεταγλωττιστής όπου βρίσκει την λέξη LdrPin την αντικαθηστά με το 1 πού είναι το pin A1 στο Arduino
                  // Το pin A1 Διαβάζει αναλογική είσοδο τύπου 0-255 ανάλογα με την φωτεινότητα του περιβάλοντος 

#define P_Delay 10 // protocoll delay in ms
// Ορισμός διευθηνσης GrifEye
#define GridDeviceAddress 0b1101001

/*Συναρτήσεις και ρουτίνες προγράμματος */
void Gym62_PinSetup(void); // Ρυθμίζει τις λειτουργίες I/O του Arduino σε σχέση με το Gym62 Shield
void Gym62_ObjtacleAvoid(void);// Αποφυγή εμποδίων 
void Gym62_RobotAction(void); // Αποφασιζει αν θα τεθεί σε κήνηση.
void Gym62_CheckCommands (void); // Ελέγχει θήρα Uart και διαβάζει το πρωτόκολλο εντολών εισόδου
void Gym62_GetGridRow(unsigned char ,  float *); // Διαβάζει τα δεδομένα από μία δεδομένη γραμμή θερμικών του GridEye
unsigned int Gym62_GetThermalData (void); // Διαβάζει όλα τα δεδομένα του αισθητήρα Grid Thermal Array και τα τοποθετεί σε πινακα μνήμης για επεξεργασία
void Gym62_DirectionSet (unsigned char ,int ); // θέτει το όχημα σε κίνηση ή ακινησία στην καθορισμένη κατεύθυνση και διεύθυνση
void Gym62_MotorsMove(unsigned char ,unsigned char,unsigned char ,unsigned char ); // Θέτει την ταχύτητα και την φορά περιστροφής των κινητήρων, ανάλογα με τις εντολές εισόδου.   
unsigned int Gym62_GetDistance (unsigned char); // Διαβάζει και επιστρέφει την απόσταση των αντικειμένων από το όχημα από μπροστά και από πίσω.
void Gym62_SendThermalData(void); // απόστέλει στην Uart τα δεδομένα του θερμικού αισθητήρα. 
void Gym62_SendMetricsData(void); // αποστέλλει στην Uart τα δεδομένα κίνησης και περιβάλλοντος του οχήματος. 
void Gym62_CalculateThermalLine(float *); // Υπολογίζει την θερμική γραμμή με την μέγιστη θερμοκρασία στο πεδίο και βρίσκει το σημείο κατεύθυνσης.
float Gym62_GetGridIntTemp(void); // διαβάζει την εσωτερική θερμοκρασία του Αισθητήρα για να συγκρίνει με τις ακτινοβολούμενες θερμότητες.
bool Gym62_On_Timer(unsigned int); // Free Run timer working with flags
bool Gym62_Off_Timer(unsigned int); // Free Run timer working with flags


// Ορισμός Μεταβλητών
unsigned char RunningLspeed=255; // Μεταβλητή ταχύτητας αριστερού μοτέρ.
unsigned char RunningRspeed=255; // Μεταβλητή ταχύτητας δεξιού μοτέρ.
float InternalTemp=10; // Μεταβλητή εσωτερικής θερμοκρασίας αισθητήρα θερμοτητας.
float TopTemperature=10; // Μεταβλητή θερμοκρασίας περιβάλοντος.
unsigned int Light=10; // Ambient Light

float GridMatrix[8][8]; // Πίνακας Χ,Υ τιμών θερμοκρασιών
float GridLineY[8]; // Πίνακας ανάγνωσης γραμμής θερμοκρασιών αισθητήρα Grid  
float GridMaxLine[8]; // Πίνακας τις γραμμής όπου εμφανίζεται ή μέγιστη τιμή θερμότητας

//float ThermalLine[8];
int ThermalPeak[8]; // δείχνει τη θέση της υψηλότερης θερμοκρασίας με 1
float TempDiff[8];

unsigned char SerialByte=0;
char Buffer[10];
unsigned char BufferCnt=0;
unsigned int Number=0;
unsigned char DataToSend=0;
unsigned char RobotSpeed=250; // τελική ταχύτητα ρομπότ
unsigned char SetRobotSpeed=250; // επιλεγμένη ταχύτητα ρομπότ
int X_Address;
int PeakPointer=0;
unsigned char SpeedL=0;
unsigned char SpeedR=0;
int Direction='F';
int TempTriger=36;
float TrigerDiff=0;  
int LookUpTable[8]={-100,-75,-50,-25,25,50,75,100}; // Πίνακας με το ποσοστό της ταχύτητας των ερπυστριών σε σχέση με την θέση της μέγηστης θερμότητας στον ορίζοντα
unsigned int DistanceGetFD=50;
unsigned int DistanceGetBD=50;
unsigned int DistanceSetFD=10;
unsigned int DistanceSetBD=10;

unsigned int TimerMove=100;
unsigned int TimerStop=500;
bool FireFlag=0; 
bool AutoStartFlag=0;
bool MoveFlag=1;

unsigned long On_TmrNowVal=0;
unsigned long On_TmrPreVal=0;
bool Tmr1Exp_Flag=0;
bool On_TmrEn_Flag=0;
unsigned long Off_TmrNowVal=0;           
unsigned long Off_TmrPreVal=0;
bool Tmr2Exp_Flag=0;
bool Off_TmrEn_Flag=0;

bool GotDataCpl_Flag=false;
bool GotDataCpr_Flag=false;

bool ManualFront_Flag=false;
bool ManualBack_Flag=false;
bool ManualRight_Flag=false;
bool ManualLeft_Flag=false;

void setup ()
{
  Wire.begin(); // συδνέστε το δίαυλο i2c(διεύθηνση προαιρετική για master)
  Serial.begin(115200); 
  Gym62_PinSetup(); //  εισαγωγή ασπίδας Gym62
  
   
}

void loop ()
{


Gym62_GetThermalData(); // Διάβασε το Grid Eye και βάλτε το αποτέλεσμα σε ένα πίνακα 
InternalTemp=Gym62_GetGridIntTemp(); // Διάβασε την εσωτερίκη θερμοκρασία του Grid Eye

TrigerDiff=TempTriger-InternalTemp;  // Υπολόγισε την διαφορά της θερμοκρασίας πυροδοτησης από την εσωτερική.   
if (TempTriger<0) TempTriger=0;

Gym62_CalculateThermalLine(GridMaxLine); // υπολόγισε τόν ορίζοντα θερμοτήτων
Gym62_RobotAction(); // Πλοηγήσου με βάση τον ορίζοντα θερμοτήτων 

DistanceGetFD=Gym62_GetDistance('F'); // διάβασε μπροστινή απόσταση
//DistanceGetBD=Gym62_GetDistance('R'); // διάβασε πισινή απόσταση

if (DistanceGetFD<DistanceSetFD) { MoveFlag=0; Gym62_ObjtacleAvoid();} // Αν υπάρχει εμπόδιο μπροστά, απέφευγε το
//if (DistanceGetBD<DistanceSetBD) { MoveFlag=0; Gym62_ObjtacleAvoid();} // Αν υπάρχει εμπόδιο μπροστά, απέφευγε το

if (AutoStartFlag==false) // Αν βρίσκεσαι σε αναμονή επιτρέπεται ή χειροκίνητη λειτουργία
{
  if (ManualFront_Flag==true) 
  {
    Gym62_MotorsMove('F',RobotSpeed,'F',RobotSpeed);
  }
  else if (ManualBack_Flag==true)
  {
    Gym62_MotorsMove('R',RobotSpeed,'R',RobotSpeed);
  }
  else if (ManualRight_Flag==true)
  {
    Gym62_MotorsMove('F',RobotSpeed-RobotSpeed*50/100,'R',RobotSpeed-RobotSpeed*50/100);
  }
  else if (ManualLeft_Flag==true)
  {
    Gym62_MotorsMove('R',RobotSpeed-RobotSpeed*50/100,'F',RobotSpeed-RobotSpeed*50/100);
  }

}

Light=analogRead(LdrPin); // δίαβασε Light Dependent Resistor ( Φωτοκύταρο )
TopTemperature=analogRead(Lm35Pin)/2; // διάβασε θερμόμετρο ατμοσφαιρικής θερμοκρασίας LM35

Gym62_SendMetricsData(); // Αποστολή δεδομενων μετρησεων χρησιμοποιώντας το πρωτόκολλο
Gym62_SendThermalData(); // Αποστολη πίνακα θερμικών φωρτίων και ορίζοντα θερμικής γραμμής χρησιμοποιώντας το πρωτόκολλο
Gym62_CheckCommands(); // Ελεγχος για δεδομενα στο σειριακο buffer ( δεδομένα απομακρισμένου ελέγχου )

  

}// loop


/**********************************************************************************************************/
void Gym62_PinSetup(void)
{
  /* 
   *  Ρυθμίζει τις λειτουργίες I/O του Arduino σε σχέση με το Gym62 Shield
   */
  
  
  // H-Bridge Port Settings
  pinMode(PWM_L, OUTPUT);  
  pinMode(PWM_R, OUTPUT);
  pinMode(DIR_L, OUTPUT);
  pinMode(DIR_R, OUTPUT);
  // ορίστε την αρχική κατάσταση των ηλεκτρικών μοτέρ
  analogWrite(PWM_L, 0);  //ρυθμίστε τα δύο μοτέρ να λειτουργούν στο 0
  analogWrite(PWM_R,0);  
  
  // Distance Sensors Port Settings
  pinMode(UltraTrigF, OUTPUT);     // ενεργοποιήστε το triger του μπροστινού αισθητήρα 
  pinMode(UltraEchoF, INPUT);     // αρχικοποιήστε την ακίδα Echo του μπροστινού αισθητήρα 
  pinMode(UltraTrigR, OUTPUT);     // αρχικοποιήστε το triger του μπροστινού αισθητήρα 
  pinMode(UltraEchoR, INPUT);     // αρχικοποιήστε την ακίδα Echo του μπροστινού αισθητήρα 
 
}


void Gym62_RobotAction(void)
{
  /*
   * Σε αυτή την ρουτινα το όχημα αποφασιζει αν θα κινηθεί ή οχι 
   */
  // χρησιμοποιήστε μια συνθήκη για να λειτουργήσει χειροκίνητα ή αυτόματα( AutoStartFlag )
  if (FireFlag==true && AutoStartFlag==true && MoveFlag==true   )
  { // φωτιά εντοπίστηκε το ρομπότ κινείται προς τη φωτιά
    RobotSpeed=SetRobotSpeed;  
    Gym62_DirectionSet (Direction,X_Address);
    Gym62_On_Timer(0); // σαφείς χρονομετρητές αναζήτησης 
    Gym62_Off_Timer(0);
    Off_TmrEn_Flag=true; // ενεργοποιήστε το διακόπτη
  }
  
  else if (AutoStartFlag==true && MoveFlag==true) // φωτιά δεν εντοπίστηκε το ρομπότ ενεργοποιεί την λειτουργεία αναζήτησης 
    {
      RobotSpeed=180; // θέστε την ταχύτητα σταθερά 180
     
      if (Off_TmrEn_Flag==true && Gym62_Off_Timer(TimerStop)==true) // δοκιμάστε αν η Gym62_On_Timer είναι ενεργοποιημένη και αν έχει επιτευχθεί η επιθυμητή τιμή 
      {
        // if yes start motors        
        Gym62_MotorsMove('F',100,'R',100); // οι κινητήρες ξεκινούν 
        Off_TmrEn_Flag=false;  // απενεργοποιήση χρονοδιακόπτη  
        On_TmrEn_Flag=true; // ενεργοποιήσε Gym62_On_Timer 
        Gym62_On_Timer(0); // διαγράψτε το χρονόμετρο για να ξεκινήσει καινούργια sesion
      }
      

      if (On_TmrEn_Flag==true && Gym62_On_Timer(TimerMove)==true) // δοκιμάστε αν Gym62_On_Timer είναι ενεργοποιημένη και αν έχει επιτευχθεί η επιθυμητή τιμή 
      {
        //αν ναι σταμάτα τα μοτέρ 
        Gym62_MotorsMove('S',120,'S',160);
        On_TmrEn_Flag=false; // απενεργοποιήστε Gym62_On_Timer 
        Off_TmrEn_Flag=true; // ενεργοποιήστε Gym62_Off_Timer 
        Gym62_Off_Timer(0);   // διαγράψτε το χρονόμετρο για να ξεκινήσει καινούργια sesion 
      }
  
    }//if (AutoStartFlag==1)
    else //if (AutoStartFlag==0)
      { // το ρομπότ βρίσκεται σε λειτουργία αναμονής ( Initial condition )
      Gym62_MotorsMove('S',120,'S',160); // σταματήστε τα μοτέρ 
      Off_TmrEn_Flag=true; //ενεργοποιήστε το χρονοδιακόπτη
      Gym62_On_Timer(0); // σαφείς χρονομετρητές αναζήτησης 
      Gym62_Off_Timer(0);
      }

   
}// Gym62_RobotAction


void Gym62_ObjtacleAvoid(void)
{
 // Ρουτίνα αποφυγής εμποδίων 

 int TempSpeed=0;
 TempSpeed=RobotSpeed*80/100;
 if (MoveFlag==1) {
 return;
 }
Gym62_MotorsMove('S',0,'S',0);
delay (200);
Gym62_MotorsMove('R',RobotSpeed-TempSpeed,'R',RobotSpeed);
delay (500);
Gym62_MotorsMove('S',0,'S',0);
delay (200);
Gym62_MotorsMove('F',RobotSpeed,'F',RobotSpeed);
delay(500);
Gym62_MotorsMove('S',0,'S',0);
MoveFlag=1;
 
}


void Gym62_CheckCommands (void)
{
  /* 
   *  Ελέγχει θήρα Uart και διαβάζει το πρωτόκολλο εντολών εισόδου
   */
  char n;
  int temp;
  int Counter;
  int data;
  char SerialData[10];
  String Str;
  String NumStr;
   //ανάγνωση πρωτοκόλλου από σειριακή μέθοδο 
if(Serial.available()>4) // ελέγχει αν υπάρχουν περισσότερα από 4 bytes διαθέσιμα
{
  Counter=Serial.available(); // αντιγράψτε το μήκος των δεδομένων

  for (n=0;n<Counter;n++) 
  {
    data=Serial.read(); // διάβασε τα δεδομένα και πρόσθεστα στον πίνακα 
    Str+=(char)data; // ως χαρακτήρες
  }

    
  if (Str.startsWith("*Tr-") )
  {
    NumStr=Str.substring(4); //αριθμός αντιγράψων στο string από την θέση
    TempTriger=NumStr.toFloat(); // μετατρέψτε τον αρθμό του string σε float ή ακέραιο
    return;
  }
  
  if (Str.startsWith("*Tm-") )
  {
    NumStr=Str.substring(4);
    TimerMove=NumStr.toInt();
    return;
  }
  
  if (Str.startsWith("*Ts-"))
  {
    NumStr=Str.substring(4);
    TimerStop=NumStr.toInt();
    return;
  }
  
  if (Str.startsWith("*Sp-") )
  {
    NumStr=Str.substring(4);
    SetRobotSpeed=NumStr.toInt();
    RobotSpeed=SetRobotSpeed;
    return;
  }
  
 if (Str.startsWith("*Str")) 
 { 
  AutoStartFlag=1;
  return;
  }
 if (Str.startsWith("*Stp"))
 {
  AutoStartFlag=0;
   return;
  }

 if (Str.startsWith("*Fwd"))
          {
            ManualFront_Flag=true; 
            ManualBack_Flag=false; 
            ManualRight_Flag=false;
            ManualLeft_Flag=false;
            return;
          }  
if (Str.startsWith("*Bwd"))
          {
            ManualFront_Flag=false; 
            ManualBack_Flag=true; 
            ManualRight_Flag=false;
            ManualLeft_Flag=false;  
            return;
          }
if (Str.startsWith("*Rwd"))
          {
            ManualFront_Flag=false; 
            ManualBack_Flag=false; 
            ManualRight_Flag=true;
            ManualLeft_Flag=false;  
            return;
          }
if (Str.startsWith("*Lwd"))
          {
            ManualFront_Flag=false; 
            ManualBack_Flag=false; 
            ManualRight_Flag=false;
            ManualLeft_Flag=true;            
            return;
          }
if (Str.startsWith("*Swd"))
          {
            ManualFront_Flag=false; 
            ManualBack_Flag=false; 
            ManualRight_Flag=false;
            ManualLeft_Flag=false; 
            return;           
          }

if (Str.startsWith("*Df-"))
  {
    NumStr=Str.substring(4);
    DistanceSetFD=NumStr.toInt();
    return;
  } 
  
  if (Str.startsWith("*Db-"))
  {
    NumStr=Str.substring(4);
    DistanceSetBD=NumStr.toInt();
    return;
  }
Serial.flush();

  
  
  //AutoStartFlag=1;
 

}   

  
}// Gym62_CheckCommands

/**********************************************************************************************************/
void Gym62_SendMetricsData(void)
{

/*
 * αποστέλλει στην Uart τα δεδομένα κίνησης και περιβάλλοντος του οχήματος. 
 * 
 */
  
  String MessagePost;
  String Temp;

   
    Temp=String(TopTemperature,1);
    MessagePost=String("!tmp-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);

    Temp=String(Light,DEC);
    MessagePost=String("!lgt-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);

    Temp=String(SpeedR,DEC); 
    MessagePost=String("!spr-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);

    Temp=String(SpeedL,DEC);
    MessagePost=String("!spl-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);

    Temp=String(DistanceGetFD,DEC);
    MessagePost=String("!dtf-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);

    Temp=String(DistanceGetBD,DEC);
    MessagePost=String("!dtb-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);
    
       
    Temp=String(InternalTemp,1);
    MessagePost=String("!htt-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);

   
    Temp=String(SetRobotSpeed,DEC);
    MessagePost=String("!spd-"+Temp);
    Serial.println(MessagePost);  
    delay(P_Delay);

  

    Temp=String(TempTriger,DEC);
    MessagePost=String("!trg-"+Temp);
    Serial.println(MessagePost);
    delay(P_Delay);
}// Send metrics data

/**********************************************************************************************************/
void Gym62_SendThermalData(void)
{
  /* 
   * απόστέλει στην Uart τα δεδομένα του θερμικού αισθητήρα.  
   * 
   */
unsigned int x;
unsigned int y;
String StartString;
String SendMaxString;
String SendPtrString;
String RowNum;
String TempStr;

// πρώτα στείλε δεδομένα θερμοκρασίας
for(y=0; y<8; y++) // κάντε για όλες τις τιμές y από 0 έως 7
  {
  RowNum=String(y); //μετατρέψτε τον αριθμό y σε string χαρακτήρων και βάλτε τον στη μεταβλητή Num 
  StartString=String("!ro"+RowNum); // συνθέστε το string έναρξης  
  Serial.print(StartString);
  for (x=0;x<8;x++)
    {
      TempStr=String(GridMatrix[x][y],1); // μετατρέψτε τα δεδομένα float σε string με ένα δεκαδικό ψηφίο  
      Serial.print("-"+TempStr);
        
    }//for x 
    Serial.println();     
    //Serial.print('\r'+'\n');     
  delay(P_Delay); 
  }// for y


  // Δεύτερον στείλτε το GridMaxLine. Αυτή είναι η σειρά με τη μέγιστη θερμοκρασία στη matrix
  StartString=String("!hrz"); // συνθέστε το sting έναρξης
  for (x=0;x<8;x++)
  {
     TempStr=String(GridMaxLine[x],1); // μετατρέψτε τα δεδομένα float σε string με ένα δεκαδικό ψηφίο
      SendMaxString=String(SendMaxString+"-"+TempStr); // συνθέστε το string θερμικών δεδομένων και προσθέστε το στην αρχή του string         
  }//for x 
  Serial.println(StartString+SendMaxString);// String sould be : !hrz-00.0-00.0-00.0-00.0-00.0-00.0-00.0-00.0 
  //Serial.print(StartString+SendString+'\r'+'\n');// String sould be : !hrz-00.0-00.0-00.0-00.0-00.0-00.0-00.0-00.0 
  delay(P_Delay);

  // Τρίτον στείλε το Pointer table. Αυτό είναι το υπολογισμένο σημείο φωτιάς στον ορίζοντα
  StartString=String("!ptr"); // συνθέστε το string  έναρξης 
  for (x=0;x<8;x++)
  {
     TempStr=String(ThermalPeak[x]); // 
      SendPtrString=String(SendPtrString+"-"+TempStr); // συνθέστε το string θερμικών δεδομένων και προσθέστε το στην αρχή του string        
  }//for x 
  Serial.println(StartString+SendPtrString);// το string πρέπει να είναι : !ptr-0-0-0-0-0-0-0-0 
  delay(P_Delay);

  

}// SentThermalData



/**********************************************************************************************************/
void Gym62_DirectionSet (unsigned char Dir,int address)
{

/* 
 * θέτει το όχημα σε κίνηση ή ακινησία στην καθορισμένη κατεύθυνση και διεύθυνση
 * Με βάση την επιλεγμένη ταχύτητα του οχήματος υπολογίζεται ή μείωση τις ταχύτητας της μίας ερπύστριας ανάλογα με την διεύθυνση και κατεύθυνση εισόδου
 * Η μία ερπύστρια κινείται με την μέγιστη ταχύτητα και ή άλλη με την υπολογισμένη.
*/

int AddressSpeed=0; // Αυτός ο καταχωρητής αποθηκεύει την υπολογισμένη ταχύτητα με την οποία θα κινηθεί ή μεταβαλλόμενη ερπύστρια  

if(address>=0) AddressSpeed = RobotSpeed - ( (address*RobotSpeed)/100 );
else  AddressSpeed = RobotSpeed + ( (address*RobotSpeed)/100 );

if (AddressSpeed==0) AddressSpeed=30; // Η ερπύστρια δεν μπορεί να είναι ακίνητη για λόγους προστασίας από τις τριβές.

 
if ( (Dir=='F' || Dir=='f') && address>=0 ) Gym62_MotorsMove('F',RobotSpeed,'F',AddressSpeed);
   else if ( (Dir=='F' || Dir=='f') && address<0 ) Gym62_MotorsMove('F',AddressSpeed,'F',RobotSpeed);

 
if ( (Dir=='R' || Dir=='r') && address>=0 ) Gym62_MotorsMove('R',AddressSpeed,'R',RobotSpeed);
   else if ( (Dir=='R' || Dir=='r') && address<0 )Gym62_MotorsMove('R',RobotSpeed,'R',AddressSpeed);


 if ( Dir=='S' || Dir=='S') Gym62_MotorsMove('S',0,'S',0);  
  
 }

/**********************************************************************************************************/
void Gym62_MotorsMove(unsigned char Ldir,unsigned char Lspeed,unsigned char Rdir,unsigned char Rspeed)
{
  /*
   * Θέτει την ταχύτητα και την φορά περιστροφής των κινητήρων, ανάλογα με τις εντολές εισόδου. 
   * Ldir -> Left motor direction F=front / R=reverse / S=Stop
   * Rdir -> Right motor direction F=front / R=reverse / S=Stop
   * Lspeed -> Left motor speed ( Min 0 - Max 250 )
   * Rspeed -> Right motor speed ( Min 0 - Max 250 )
   * 
   */
  
    
  if (Lspeed>250) Lspeed=250;
  if (Rspeed>250) Rspeed=250;

  SpeedR=Rspeed;
  SpeedL=Lspeed;
  //Ελέγξτε την κατεύθυνση του αριστερού μοτερ
  if (Ldir=='F') digitalWrite(DIR_L,FORWARD);
  else if (Ldir=='R') digitalWrite(DIR_L,REVERSE);
  //αλλιώς analogWrite (PWM L, 0). // εάν οχι τότε δεν σταματάει ταυτόχρονα
  
  // Ελεξτε την κατευθυνση του δεξιου μοτερ
  if (Rdir=='F') digitalWrite(DIR_R,FORWARD);
  else if (Rdir=='R') digitalWrite(DIR_R,REVERSE);
  //αλλιώς analogWrite (PWM L, 0). // εάν οχι τότε δεν σταματάει ταυτόχρονα 
   
  // πριν κάνετε την αναλογική εγγραφή, ελέγξτε εάν είναι απαραίτητη
  if (Ldir=='S') { digitalWrite(PWM_L,0); RunningLspeed=0; }
   else if (Lspeed!=RunningLspeed) { analogWrite(PWM_L,Lspeed); RunningLspeed=Lspeed;}
  
  if (Rdir=='S') { digitalWrite(PWM_R,0); RunningRspeed=0; }
    else if (Rspeed!=RunningRspeed) { analogWrite(PWM_R,Rspeed); RunningRspeed=Rspeed;}
}

void Gym62_ManualMove (unsigned char flags)
{
  
}

/**********************************************************************************************************/
unsigned int Gym62_GetDistance (unsigned char Udir)
{
  /*
   * Διαβάζει και επιστρέφει την απόσταση των αντικειμένων από το όχημα από μπροστά και από πίσω σε εκατοστά του μέτρου.
   * Είσοδος Udir='F' -> Front / Udir='R' -> Rear
   * 
   */
  
  
    long Duration=0; // 32 bit Register κρατάει την τιμή του Bare Readed From Sensor
    unsigned int Distance=0; // 16 bit Register. Διατηρεί την τιμή σε cm

    // Χειρισμός του σήματος ενεργοποίησης του αισθητήρα
    if (Udir=='F')
    {
    digitalWrite(UltraTrigF, LOW); // επαναφορά στη χαμηλή 
    delayMicroseconds(2);
    digitalWrite(UltraTrigF, HIGH); // ρύθμιση ύψους για 10uS   
    delayMicroseconds(6);  
    digitalWrite(UltraTrigF, LOW); // και στην συνεχέχεια πίσω στο χαμηλό
    
    // SENSOR READING FUNCTION
    Duration = pulseIn(UltraEchoF, HIGH,70000); // επιστρεφόμενη διάρκεια παλμού. Αποδράστε μετά από 70mS αν δεν εντοπιστεί έγκυρο σήμα
    Distance = 0.0001*((float)Duration*340)/2;//58; // υπολογίστε την τιμή της απόστασης σε cm
    if ( Distance>250 ) Distance=250; // ακύρωση ψευδών τιμών μεγαλύτερων των 250 Cm
    }
    else if (Udir=='R')
    {
  // Handle Triger Signal to Sensor 
    
    digitalWrite(UltraTrigR, LOW); // επαναφορά σε χαμηλή
    delayMicroseconds(2);
    digitalWrite(UltraTrigR, HIGH); // ρύθμιση ύψους σε 10uS   
    delayMicroseconds(6);  
    digitalWrite(UltraTrigR, LOW); // και στη συνέχεια πίσω στο χαμηλό
    
    // SENSOR READING FUNCTION
    Duration = pulseIn(UltraEchoR, HIGH,70000); // Επιστρεφόμενη διάρκεια παλμού. Αποδράστε μετά από 70mS αν δεν εντοπιστεί έγκυρο σήμα
    Distance = 0.0001*((float)Duration*340)/2;//58; // υπολογίστε την τιμή της απόστασης σε cm
    if ( Distance>250 ) Distance=250; // ακύρωση ψευδών τιμών μεγαλύτερων των 250 Cm
    }  
    
    return Distance;  // Επιστροφή με την υπολογισμένη τιμή.
  
  
}


/**********************************************************************************************************/
unsigned int Gym62_GetThermalData (void)
{
  /* 
   *  Διαβάζει όλα τα δεδομένα του αισθητήρα Grid Thermal Array και τα τοποθετεί σε πινακα μνήμης για επεξεργασία
   */
 unsigned int x=0;
 unsigned int y=0;
 float Max=0.0;
 int MaxTempY=0;

 for (y=0; y<8; y++)
 {
  
  Gym62_GetGridRow(y, GridLineY);

  for(x=0; x<8; x++)
  {
    GridMatrix[x][y]=GridLineY[x];
    if (GridLineY[x]>Max) {MaxTempY=y; Max=GridLineY[x];}
    
  }// για x
 
 } // για y

  for(x=0; x<8; x++)  GridMaxLine[x]=GridMatrix[x][MaxTempY];  // αντιγράψτε την τιμή με τη μέγιστη θερμοκρασία
 
}

/**********************************************************************************************************/
// Thermal Grid Reading Routine 
void Gym62_GetGridRow(unsigned char YRaw,  float *data)
{
  /*
   * Διαβάζει τα δεδομένα από μία δεδομένη γραμμή θερμικών του GridEye
   */
  
  
    unsigned char DataAddress=0;
    unsigned char GridRegs[16];
    unsigned char n;
    unsigned char x=0;
    unsigned char Hbyte;
    unsigned char Lbyte;
    unsigned char Temp;
    int WordData;
  
    if (YRaw==0) DataAddress=0x80;
    if (YRaw==1) DataAddress=0x90;
    if (YRaw==2) DataAddress=0xA0;
    if (YRaw==3) DataAddress=0xB0;
    if (YRaw==4) DataAddress=0xC0;
    if (YRaw==5) DataAddress=0xD0;
    if (YRaw==6) DataAddress=0xE0;
    if (YRaw==7) DataAddress=0xF0;
  
    Wire.beginTransmission(GridDeviceAddress);
    Wire.write(DataAddress);  // στείλτε τα πρώτα δεδομένα για ανάγνωση
    delayMicroseconds(20);
    Wire.endTransmission();    // σταματήστε τη μετάδοση
    Wire.requestFrom(GridDeviceAddress,16);
    delayMicroseconds(300);
    n=0;
    while(Wire.available())    // ο slave μπορεί να στείλει λιγότερα από τα ζητούμενα 
    {
    GridRegs[n] = Wire.read();    // λαμβάνουν ένα byte ως χαρακτήρα
    n++;
    }
        
    x=0;
    for (n=0;n<16;n+=2)
    {
   Lbyte= GridRegs[n]; // διαβάστε χαμηλό byte
   Hbyte= GridRegs[n+1]; //διαβάστε υψηλό byte   
   Temp=Hbyte;
   if(Temp & 0b00001000) Temp |= 0b11111000;
   WordData=(Temp << 8) | Lbyte;
   data[x]= WordData / 4; // μετέτρεψε το σε ℃ 
    if (data[x]<0 && data[x]>120) data[x]=0x00; 
   x++;   
    } 
  
    //SendGridRowData(data);
   
}// ReadGridData routine

/**********************************************************************************************************/
void Gym62_CalculateThermalLine(float *data)
{
  /*
   * Υπολογίζει την θερμική γραμμή με την μέγιστη θερμοκρασία στο πεδίο και βρίσκει το σημείο κατεύθυνσης.
   */
  int n=0;
  int MaxDif=0;
  int p=10;
  

  // βρείτε και βάλτε σε ένα table τη διαφορά θερμοκρασιών από τις τιμές του αισθητήρα εσωτερικής θερμοκρασίας 
  for(n=0;n<8;n++) 
  { 
    TempDiff[n]=data[n]-InternalTemp;
    if (TempDiff[n]<0) {TempDiff[n]=0;}  
  }


  // Από τις διαφορές θερμοκρασίας βρείτε την μέγιστη τιμή
  // Όταν αυτό υπερβαίνει την επιλεγμένη διαφορά triger 
  for (n=0;n<8;n++) 
  {
    ThermalPeak[n]=0;
    if(TempDiff[n]>MaxDif && TempDiff[n]>TrigerDiff ) 
    { 
      MaxDif=TempDiff[n]; // μέγιστη διαφορά
      p=n; // δείκτης θέσης στον πίνακα 
    }    
  }

if (p>=0 && p<=7)  
{ 
  ThermalPeak[p]=1; // χρησιμοποιήστε δείκτη για να δείξετε σε έναν πίνακα μηδενικών τη μέγιστη διαφορά
  FireFlag=1; 
  Direction='F';
  PeakPointer=p;
  X_Address=LookUpTable[p];
  }
else  { X_Address=0; Direction='S'; FireFlag=0;}
  
  X_Address=LookUpTable[p];
}

/**********************************************************************************************************/
float Gym62_GetGridIntTemp(void)
{
  
  unsigned char ThermistorReg[2];
  unsigned char n;
  unsigned char Hbyte;
  unsigned char Lbyte;
  unsigned char Temp;
  int RawBits;
  
  Wire.beginTransmission(GridDeviceAddress);
  Wire.write(0x0E);  // στείλτε τα πρώτα δεδομένα για ανάγνωση
  delayMicroseconds(20);
  Wire.endTransmission();    // σταμάτα τη μετάδοση
  delayMicroseconds(20);
  Wire.requestFrom(GridDeviceAddress,2);
  delayMicroseconds(300);
  
    n=0;
    while(Wire.available())    // ο slave μπορεί να στείλει λιγότερα από όσα ζητήθηκαν
    {
    ThermistorReg[n] = Wire.read();    // λάβε ένα byte ως χαρακτήρα
    n++;
    }
  
  Lbyte= ThermistorReg[0]; // διάβασε χαμηλό byte 
  Hbyte= ThermistorReg[1]; // διάβασε υψυλό byte
  Temp=Hbyte;
  if(Temp & 0b00001000) Temp |= 0b11111000;
  RawBits=(Temp << 8) | Lbyte;
  RawBits=RawBits * 0.0625; // μετέτρεψε το σε ℃  
  
  if (RawBits<0 && RawBits>120) RawBits=0x00;
        
  
  return RawBits; 

}

/**************************************************************************************************/




bool Gym62_On_Timer(unsigned int Data)
{
  // εάν τα δεδομένα είναι 0,διαγράφει το χρονοδιακόπτη αντιγράφοντας τώρα το χρόνο και επιστρέφει true
  if (Data==0) 
  {
   On_TmrPreVal=millis();
   return true;
  }
  
  if (On_TmrEn_Flag==false) return false;
  On_TmrNowVal=millis();
  if (On_TmrNowVal-On_TmrPreVal>Data)
  {
    On_TmrPreVal=On_TmrNowVal;
    return true;
  } 
  else return false;                        
}

bool Gym62_Off_Timer(unsigned int Data)
{
 
  // εάν τα δεδομένα είναι 0,διαγράφει το χρονοδιακόπτη αντιγράφοντας τώρα το χρόνο και επιστρέφει true
  if (Data==0) 
  {
   Off_TmrPreVal=millis();
   return true;
  }
  
  if (Off_TmrEn_Flag==false) return false;
  Off_TmrNowVal=millis();
  if (Off_TmrNowVal-Off_TmrPreVal>Data)
  {
    Off_TmrPreVal=Off_TmrNowVal;
    return true;
  }
  return false;
}
