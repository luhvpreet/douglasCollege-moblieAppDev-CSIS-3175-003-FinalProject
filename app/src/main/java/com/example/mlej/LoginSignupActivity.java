package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSignupActivity extends AppCompatActivity {

    DatabaseHelper db;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Button btnLSLogin = findViewById(R.id.btnLSLogin);
        Button btnLSSignup = findViewById(R.id.btnLSSignup);

        db = new DatabaseHelper(LoginSignupActivity.this);
        if(db.getUserCount()<10) initData(); //reset and insert dummy records if too few users are in the system

        //clicking on the Login button will go to the Login activity
        btnLSLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //clicking on the signup button will go to the SignupAccountType activity
        btnLSSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignupActivity.this, SignupAccountTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    //dummy info for users, appointments, providerservices, etc:
    void initData(){

        System.out.println("Start deleting all records...");

        //delete all records in all database (dummy users / appointments, etc)
        db.deleteALLRecords();

        db.addServices(1,"Electronic System Check", 250.99);
        db.addServices(2,"New Tires or Changing Tires", 859.99);
        db.addServices(3,"AC and Heating Repair", 649.99);
        db.addServices(4,"Brakes of Brake Repair", 199.99);
        db.addServices(5,"Lights, Wipers and Accessories", 119.99);
        db.addServices(6,"Belts and Hoses", 299.99);
        db.addServices(7,"Fluid Changes", 89.99);
        db.addServices(8,"Exhaust System Services", 599.99);
        db.addServices(9,"Steering and Suspension", 899.99);
        db.addServices(10,"Batteries and Charging", 99.99);

        // from 0 to 5 is provider, 6 to 43 is taker
        db.addUser(0,"Eric Smith","a@a.com","1234","3451380427","1439 Elmwood Avenue, Vancouver, BC","G7V 6K2","WheelzRUs");
        db.addUser(0,"Lovepreet Johnson","b@b.com","1234","6469986203","720 Pine Street, Vancouver, BC","V5K 3M6","Great Auto");
        db.addUser(0,"Matthew Jackson","c@c.com","1234","5542756686","2084 Oak Lane, Vancouver, BC","V5K 3V1","Apex Auto Works");
        db.addUser(0,"Jichi Davis","d@d.com","1234","3625060698","3613 Maple Street, Richmond, BC","V6V 3R3","GearUpGo");
        db.addUser(0,"Helen Wong","e@e.com","1234","6744486716","905 Cedar Street, Richmond, BC","V6V 4W4","RideRunners");
        db.addUser(1,"Mary Causey","f@f.com","1234","6410028215","1234 Main St, Vancouver, BC","H9R 4R6","");
        db.addUser(1,"Eldred Hayden","lucasrivera994@inboxalias.com","1234","6632073434","5678 Kingsway, Burnaby, BC","V3S 6K3","");
        db.addUser(1,"Bronwyn Durant","kylejohnson746@tempinbox.com","1234","9721854038","901 Pandora Ave, Victoria, BC","R3L 1T8","");
        db.addUser(1,"Chalice Davison","rebeccaallen271@tempmailgen.com","1234","8006260631","3456 30th Ave, Vernon, BC","V1L 6T9","");
        db.addUser(1,"Les Danell","joshuamurphy463@spammify.com","1234","9631689693","7890 Granville St, Richmond, BC","M5G 2M6","");
        db.addUser(1,"Jessy Blakely","jacobwhite984@mailinator2.com","1234","3565910966","4321 Marine Dr, West Vancouver, BC","H1L 6E7","");
        db.addUser(1,"Randell Ellison","kristinahill734@trashmailer.com","1234","5111385941","6543 152 St, Surrey, BC","B2T 1B9","");
        db.addUser(1,"Lynn Paterson","matthewjackson337@guerrillamail.net","1234","9962407977","2101 8th Ave, Prince George, BC","V9P 8L8","");
        db.addUser(1,"Cailin Holland","jennifermiller497@jetable.net","1234","8349702123","9999 Main St, Coquitlam, BC","E1C 1B4","");
        db.addUser(1,"Ronan Rush","carolineparker808@tempmails.net","1234","3031170021","1111 Douglas St, Victoria, BC","T4C 1H4","");
        db.addUser(1,"Reba Clifton","davidmartinez946@getmails.net","1234","2178753341","2222 West 4th Ave, Vancouver, BC","P6A 4S8","");
        db.addUser(1,"Tibby Everett","amandasanchez570@mail-temp.com","1234","9350712157","3333 West Broadway, Vancouver, BC","R2K 3N2","");
        db.addUser(1,"Oscar Forester","brianharris643@inboxhub.net","1234","3246678813","4444 Oak St, Vancouver, BC","V8W 3Z5","");
        db.addUser(1,"Kayla Dudley","sarahthomas924@mailcat.biz","1234","3701081843","5555 Hastings St, Burnaby, BC","J9J 3K1","");
        db.addUser(1,"Jeremiah Pearson","ericgarcia764@boximail.com","1234","5788727999","6666 Royal Oak Ave, Burnaby, BC","L6A 3R5","");
        db.addUser(1,"Hugh Whinery","kellylee734@mailsac.com","1234","2552483088","7777 Main St, Vancouver, BC","J0R 1P0","");
        db.addUser(1,"Riley Woodhams","jessicawright912@tempinbox.net","1234","4673746448","8888 University Dr, Burnaby, BC","T6E 5X9","");
        db.addUser(1,"Ness Danell","bobbydixon630@discard.email","1234","6671113484","9999 King George Blvd, Surrey, BC","H2C 2A7","");
        db.addUser(1,"Shelly Dane","alexandermorgan818@spamobox.com","1234","8371803933","12345 Marine Dr, White Rock, BC","N2C 2H1","");
        db.addUser(1,"Stormi Watts","juliawalker759@tempmailaddress.com","1234","8389419625","54321 152 St, Surrey, BC","L2T 2V7","");
        db.addUser(1,"Haven Jarvis","alicewilson602@fakeinbox.com","1234","1887865260","13579 88 Ave, Surrey, BC","G6H 7P8","");
        db.addUser(1,"Jaquelyn Horne","samuelramirez793@discardmail.com","1234","5827445489","24680 56 Ave, Langley, BC","R2M 1Z1","");
        db.addUser(1,"Joshua Lynwood","laurahall460@inboxmails.com","1234","9943533223","98765 72 Ave, Delta, BC","V4T 4R4","");
        db.addUser(1,"Rylee Morce","kevinscott985@tempmails.info","1234","2369372959","45678 Wellington Ave, Chilliwack, BC","M9R 3S7","");
        db.addUser(1,"Rhianna Burns","lisamartinez162@spammik.com","1234","6045531010","35791 Garibaldi Dr, Abbotsford, BC","B3S 1J2","");
        db.addUser(1,"Vinnie Moss","rachelturner385@guerrillamail.biz","1234","5485125585","65432 No 3 Rd, Richmond, BC","V7K 3L5","");
        db.addUser(1,"Josiah Eady","ethanross342@maildropz.com","1234","6954961254","11223 Cambie Rd, Richmond, BC","P3N 3Z5","");
        db.addUser(1,"Kai Gray","johngonzalez278@trash-me.com","1234","7081452917","88776 120 St, Delta, BC","J0K 3E0","");
        db.addUser(1,"Trenton Tipton","michelleclark802@trashmails.com","1234","8375947776","78965 108 Ave, Surrey, BC","H7A 1H8","");
        db.addUser(1,"Emily Carter","jonesjennifer734@zoho.com","1234","1689739933","11111 152 St, Surrey, BC","G7V 6K2","");
        db.addUser(1,"Nathanial Lee","edwardsjason120@protonmail.com","1234","9080150779","22222 16th Ave, Langley, BC","K8P 3M6","");
        db.addUser(1,"Sophia Davis","parkerolivia689@yahoo.com","1234","9046064566","33333 Clayburn Rd, Abbotsford, BC","V2L 3V1","");
        db.addUser(1,"Aaron Wilson","harrisdavid785@outlook.com","1234","6837167216","44444 Old Yale Rd, Chilliwack, BC","B3H 3R3","");
        db.addUser(1,"Samantha Brooks","moorebrian201@gmail.com","1234","6321719115","55555 Hope River Rd, Chilliwack, BC","J5R 4W4","");
        db.addUser(1,"Joshua Murphy","lewislauren692@icloud.com","1234","7092962090","66666 Airport Rd, Kelowna, BC","H9R 4R6","");
        db.addUser(1,"Olivia Peterson","rogerssamuel803@outlook.com","1234","5545312291","77777 Kirschner Rd, Kelowna, BC","V3S 6K3","");
        db.addUser(1,"William Richardson","cooperamanda250@protonmail.com","1234","5880051181","88888 Lawrence Ave, Kelowna, BC","R3L 1T8","");
        db.addUser(1,"Ava Mitchell","stewartmatthew899@gmail.com","1234","5106773749","3333 West Broadway, Vancouver, BC","V1L 6T9","");
        db.addUser(0,"Audrey Fortin","e2@e.com","1234","5316222422","3187 Oakwood Avenue, New West, BC","R2M 1Z1","Elite Auto Care");
        db.addUser(0,"Gabriel Gagne","e3@e.com","1234","4076135783","4821 Maple Lane, New West, BC","V4T 4R4","Motor Masters");
        db.addUser(0,"Addison Singh","e4@e.com","1234","8069882483","1026 Cedar Drive, Burnaby, BC","M9R 3S7","Drive Care");
        db.addUser(0,"Thomas Pelletier","e5@e.com","1234","7717680460","3365 Pine Street, Burnaby, BC","B3S 1J2","Top Gear");
        db.addUser(0,"Mia Gagnon","e6@e.com","1234","1509250291","3800 Elmwood Lane, Burnaby, BC","V7K 3L5","Wheel Works");
        db.addUser(0,"Lucy Bouchard","e7@e.com","1234","7722520894","2498 Ashwood Avenue, Surrey, BC","P3N 3Z5","AutoCruise");
        db.addUser(0,"Isla Gagne","e8@e.com","1234","5417289741","4439 Laurel Drive, Vancouver, BC","V5K 3E0","GearUpGo");

        db.addProviderServices(1,1);
        db.addProviderServices(1,2);
        db.addProviderServices(1,3);
        db.addProviderServices(1,4);
        db.addProviderServices(1,5);
        db.addProviderServices(1,6);
        db.addProviderServices(1,7);
        db.addProviderServices(1,8);
        db.addProviderServices(1,9);
        db.addProviderServices(2,2);
        db.addProviderServices(2,3);
        db.addProviderServices(2,4);
        db.addProviderServices(2,5);
        db.addProviderServices(3,3);
        db.addProviderServices(3,4);
        db.addProviderServices(3,5);
        db.addProviderServices(3,6);
        db.addProviderServices(3,7);
        db.addProviderServices(3,8);
        db.addProviderServices(4,4);
        db.addProviderServices(4,5);
        db.addProviderServices(4,6);
        db.addProviderServices(4,7);
        db.addProviderServices(4,8);
        db.addProviderServices(4,9);
        db.addProviderServices(5,2);
        db.addProviderServices(5,3);
        db.addProviderServices(5,4);
        db.addProviderServices(5,5);
        db.addProviderServices(5,6);
        db.addProviderServices(5,7);
        db.addProviderServices(5,8);
        db.addProviderServices(5,9);
        db.addProviderServices(5,10);
        db.addProviderServices(44,1);
        db.addProviderServices(44,2);
        db.addProviderServices(44,3);
        db.addProviderServices(44,4);
        db.addProviderServices(45,5);
        db.addProviderServices(45,6);
        db.addProviderServices(45,8);
        db.addProviderServices(46,9);
        db.addProviderServices(46,10);
        db.addProviderServices(46,1);
        db.addProviderServices(46,2);
        db.addProviderServices(47,3);
        db.addProviderServices(47,4);
        db.addProviderServices(47,5);
        db.addProviderServices(47,6);
        db.addProviderServices(48,7);
        db.addProviderServices(48,8);
        db.addProviderServices(48,9);
        db.addProviderServices(49,1);
        db.addProviderServices(49,2);
        db.addProviderServices(49,3);
        db.addProviderServices(49,4);
        db.addProviderServices(50,5);
        db.addProviderServices(50,6);
        db.addProviderServices(50,7);
        db.addProviderServices(50,8);

        db.addAppointment(6,1,"2023-03-25 10:30",1);
        db.addAppointment(7,1,"2023-03-26 13:30",1);
        db.addAppointment(8,1,"2023-03-27 19:30",1);
        db.addAppointment(9,1,"2023-03-28 10:30",1);
        db.addAppointment(10,1,"2023-03-29 14:15",0);
        db.addAppointment(11,1,"2023-03-30 17:15",0);
        db.addAppointment(12,1,"2023-03-31 10:15",0);
        db.addAppointment(13,1,"2023-04-01 13:45",0);
        db.addAppointment(14,1,"2023-04-02 19:45",1);
        db.addAppointment(15,1,"2023-04-03 10:00",1);
        db.addAppointment(16,2,"2023-04-04 14:00",0);
        db.addAppointment(17,2,"2023-04-05 17:00",0);
        db.addAppointment(18,2,"2023-04-06 10:30",1);
        db.addAppointment(19,2,"2023-04-07 13:30",0);
        db.addAppointment(20,2,"2023-04-08 19:30",1);
        db.addAppointment(21,2,"2023-04-09 10:30",0);
        db.addAppointment(22,2,"2023-04-10 14:15",1);
        db.addAppointment(23,2,"2023-04-11 17:15",0);
        db.addAppointment(24,2,"2023-04-12 10:15",1);
        db.addAppointment(25,2,"2023-04-13 13:45",0);
        db.addAppointment(26,3,"2023-04-14 19:45",0);
        db.addAppointment(27,3,"2023-04-15 10:00",0);
        db.addAppointment(28,3,"2023-04-16 14:00",0);
        db.addAppointment(29,3,"2023-04-17 17:00",0);
        db.addAppointment(30,3,"2023-04-18 10:30",1);
        db.addAppointment(31,4,"2023-04-19 13:30",1);
        db.addAppointment(32,4,"2023-04-20 19:30",1);
        db.addAppointment(33,4,"2023-04-21 10:30",1);
        db.addAppointment(34,4,"2023-04-22 14:15",0);
        db.addAppointment(35,4,"2023-04-23 17:15",0);
        db.addAppointment(36,5,"2023-04-24 10:15",1);
        db.addAppointment(37,5,"2023-04-25 13:45",1);
        db.addAppointment(38,5,"2023-04-26 19:45",0);
        db.addAppointment(39,5,"2023-04-27 10:00",0);
        db.addAppointment(40,5,"2023-04-28 14:00",1);
        db.addAppointment(41,1,"2023-04-29 17:00",0);
        db.addAppointment(42,1,"2023-04-30 10:30",1);
        db.addAppointment(43,1,"2023-05-01 13:30",0);

        db.addAppointmentServices(1,1);
        db.addAppointmentServices(2,2);
        db.addAppointmentServices(3,3);
        db.addAppointmentServices(4,4);
        db.addAppointmentServices(5,5);
        db.addAppointmentServices(6,6);
        db.addAppointmentServices(7,7);
        db.addAppointmentServices(8,8);
        db.addAppointmentServices(9,9);
        db.addAppointmentServices(10,10);
        db.addAppointmentServices(11,2);
        db.addAppointmentServices(12,3);
        db.addAppointmentServices(13,4);
        db.addAppointmentServices(14,3);
        db.addAppointmentServices(15,4);
        db.addAppointmentServices(16,7);
        db.addAppointmentServices(17,2);
        db.addAppointmentServices(18,4);
        db.addAppointmentServices(19,2);
        db.addAppointmentServices(20,8);
        db.addAppointmentServices(21,4);
        db.addAppointmentServices(22,2);
        db.addAppointmentServices(23,1);
        db.addAppointmentServices(24,6);
        db.addAppointmentServices(25,8);
        db.addAppointmentServices(26,10);
        db.addAppointmentServices(27,7);
        db.addAppointmentServices(28,5);
        db.addAppointmentServices(29,7);
        db.addAppointmentServices(30,6);
        db.addAppointmentServices(31,2);
        db.addAppointmentServices(32,1);
        db.addAppointmentServices(33,9);
        db.addAppointmentServices(34,2);
        db.addAppointmentServices(35,9);
        db.addAppointmentServices(36,8);
        db.addAppointmentServices(37,6);
        db.addAppointmentServices(38,5);
        db.addAppointmentServices(1,10);
        db.addAppointmentServices(2,7);
        db.addAppointmentServices(3,1);
        db.addAppointmentServices(4,7);
        db.addAppointmentServices(5,6);
        db.addAppointmentServices(6,6);
        db.addAppointmentServices(7,10);
        db.addAppointmentServices(8,4);
        db.addAppointmentServices(9,5);
        db.addAppointmentServices(10,1);
        db.addAppointmentServices(11,10);
        db.addAppointmentServices(12,7);
        db.addAppointmentServices(13,8);
        db.addAppointmentServices(14,5);
        db.addAppointmentServices(15,10);
        db.addAppointmentServices(16,1);
        db.addAppointmentServices(17,7);
        db.addAppointmentServices(18,8);
        db.addAppointmentServices(19,8);
        db.addAppointmentServices(20,4);
        db.addAppointmentServices(21,7);
        db.addAppointmentServices(22,8);
        db.addAppointmentServices(23,7);
        db.addAppointmentServices(24,4);
        db.addAppointmentServices(25,10);
        db.addAppointmentServices(26,8);
        db.addAppointmentServices(27,7);
        db.addAppointmentServices(28,10);
        db.addAppointmentServices(29,5);
        db.addAppointmentServices(30,1);
        db.addAppointmentServices(31,8);
        db.addAppointmentServices(32,5);
        db.addAppointmentServices(33,3);
        db.addAppointmentServices(34,6);
        db.addAppointmentServices(35,3);
        db.addAppointmentServices(36,3);
        db.addAppointmentServices(37,1);
        db.addAppointmentServices(38,10);
        db.addAppointmentServices(1,10);
        db.addAppointmentServices(2,4);
        db.addAppointmentServices(3,6);
        db.addAppointmentServices(4,8);
        db.addAppointmentServices(5,2);
        db.addAppointmentServices(6,6);
        db.addAppointmentServices(7,10);
        db.addAppointmentServices(8,7);
        db.addAppointmentServices(9,6);

        System.out.println("Finish inserting all the new dummy records.");
    }

}