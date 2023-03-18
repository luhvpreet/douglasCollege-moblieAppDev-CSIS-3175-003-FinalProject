package com.example.mlej;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSignupActivity extends AppCompatActivity {

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        Button btnLSLogin = findViewById(R.id.btnLSLogin);
        Button btnLSSignup = findViewById(R.id.btnLSSignup);

        //initData();

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

    void initData(){
        db = new DatabaseHelper(LoginSignupActivity.this);

        db.addServices(1,"Electronic System Check");
        db.addServices(2,"New Tires or Changing Tires");
        db.addServices(3,"AC and Heating Repair");
        db.addServices(4,"Brakes of Brake Repair");
        db.addServices(5,"Lights, Wipers and Accessories");
        db.addServices(6,"Belts and Hoses");
        db.addServices(7,"Fluid Changes");
        db.addServices(8,"Exhaust System Services");
        db.addServices(9,"Steering and Suspension");
        db.addServices(10,"Batteries and Charging");


        // from 0 to 5 is provider, 6 to 35 is taker
        db.addUser(0,"Eric Smith","a@a.com","1234","6153484271","1439 Elmwood Avenue, Brockville, ON","G7V 6K2");
        db.addUser(0,"Lovepreet Johnson","b@b.com","1234","9055257195","720 Pine Street, Port Coquitlam, BC","K8P 3M6");
        db.addUser(0,"Matthew Jackson","lindawilliams251@trashmail.com","1234","1072229767","2084 Oak Lane, Chambly, QC","V2L 3V1");
        db.addUser(0,"Jichi Davis","pattydavis365@guerillamail.com","1234","7137418268","3613 Maple Street, Canmore, AB","B3H 3R3");
        db.addUser(0,"Helen Wong","aaronrobinson819@getairmail.com","1234","9269245943","905 Cedar Street, Sydney, NS","J5R 4W4");
        db.addUser(1,"Meriwether Causey","douglascoleman183@maildrop.cc","1234","4706210281","1865 Birch Avenue, Pembroke, ON","H9R 4R6");
        db.addUser(1,"Eldred Hayden","lucasrivera994@inboxalias.com","1234","2289026850","2592 Spruce Street, Hanover, MB","V3S 6K3");
        db.addUser(1,"Bronwyn Durant","kylejohnson746@tempinbox.com","1234","5182628433","3190 Sycamore Lane, Saint John, NB","R3L 1T8");
        db.addUser(1,"Chalice Davison","rebeccaallen271@tempmailgen.com","1234","7984453386","1068 Cherry Street, Langford, BC","V1L 6T9");
        db.addUser(1,"Les Danell","joshuamurphy463@spammify.com","1234","2268333809","2776 Willow Lane, Iqaluit, NU","M5G 2M6");
        db.addUser(1,"Jessy Blakely","jacobwhite984@mailinator2.com","1234","8041789739","4628 Ashwood Drive, Rouyn-Noranda, QC","H1L 6E7");
        db.addUser(1,"Randell Ellison","kristinahill734@trashmailer.com","1234","7939653200","1931 Laurel Lane, Dauphin, MB","B2T 1B9");
        db.addUser(1,"Lynn Paterson","matthewjackson337@guerrillamail.net","1234","7678295924","3890 Cedar Lane, Gaspe, QC","V9P 8L8");
        db.addUser(1,"Cailin Holland","jennifermiller497@jetable.net","1234","1203345548","2281 Poplar Avenue, New Glasgow, NS","E1C 1B4");
        db.addUser(1,"Ronan Rush","carolineparker808@tempmails.net","1234","3956498009","1175 Magnolia Drive, Sarnia, ON","T4C 1H4");
        db.addUser(1,"Reba Clifton","davidmartinez946@getmails.net","1234","5861734185","2999 Juniper Lane, Moose Jaw, SK","P6A 4S8");
        db.addUser(1,"Tibby Everett","amandasanchez570@mail-temp.com","1234","3549732746","2153 Oak Street, Stratford, ON","R2K 3N2");
        db.addUser(1,"Oscar Forester","brianharris643@inboxhub.net","1234","3431107503","3565 Maple Drive, Kimberley, BC","V8W 3Z5");
        db.addUser(1,"Kayla Dudley","sarahthomas924@mailcat.biz","1234","1511473056","1657 Birch Street, Portage la Prairie, MB","J9J 3K1");
        db.addUser(1,"Jeremiah Pearson","ericgarcia764@boximail.com","1234","3848396650","4245 Spruce Lane, Sainte-Agathe-des-Monts, QC","L6A 3R5");
        db.addUser(1,"Hugh Whinery","kellylee734@mailsac.com","1234","6657428666","1732 Cedar Avenue, Dawson Creek, BC","J0R 1P0");
        db.addUser(1,"Riley Woodhams","jessicawright912@tempinbox.net","1234","5983248807","2457 Pine Lane, Caraquet, NB","T6E 5X9");
        db.addUser(1,"Ness Danell","bobbydixon630@discard.email","1234","2089640528","4337 Willow Drive, Whitehorse, YT","H2C 2A7");
        db.addUser(1,"Shelly Dane","alexandermorgan818@spamobox.com","1234","1971065205","1287 Sycamore Street, Truro, NS","N2C 2H1");
        db.addUser(1,"Stormi Watts","juliawalker759@tempmailaddress.com","1234","9366161913","3800 Elmwood Lane, Matane, QC","L2T 2V7");
        db.addUser(1,"Haven Jarvis","alicewilson602@fakeinbox.com","1234","4185570898","2498 Ashwood Avenue, Timmins, ON","G6H 7P8");
        db.addUser(1,"Jaquelyn Horne","samuelramirez793@discardmail.com","1234","9680196322","4439 Laurel Drive, Swan River, MB","R2M 1Z1");
        db.addUser(1,"Joshua Lynwood","laurahall460@inboxmails.com","1234","6083609363","1844 Poplar Lane, Corner Brook, NL","V4T 4R4");
        db.addUser(1,"Rylee Morce","kevinscott985@tempmails.info","1234","6648451840","2946 Magnolia Street, Wetaskiwin, AB","M9R 3S7");
        db.addUser(1,"Rhianna Burns","lisamartinez162@spammik.com","1234","2565449352","2175 Juniper Drive, Swift Current, SK","B3S 1J2");
        db.addUser(1,"Vinnie Moss","rachelturner385@guerrillamail.biz","1234","5206978503","3187 Oakwood Avenue, Bonaventure, QC","V7K 3L5");
        db.addUser(1,"Josiah Eady","ethanross342@maildropz.com","1234","4621698881","4821 Maple Lane, Terrace, BC","P3N 3Z5");
        db.addUser(1,"Kai Gray","johngonzalez278@trash-me.com","1234","7704311390","1026 Cedar Drive, Renfrew, ON","J0K 3E0");
        db.addUser(1,"Trenton Tipton","michelleclark802@trashmails.com","1234","8865694732","3365 Pine Street, Bathurst, NB","H7A 1H8");
        db.addUser(1,"Louella Moon","justinwright654@inboxbear.com","1234","5854515297","1909 Willow Avenue, Jasper, AB","V9Y 1N9");

        db.addProviderServices(1,1);
        db.addProviderServices(1,2);
        db.addProviderServices(1,3);
        db.addProviderServices(1,4);
        db.addProviderServices(1,5);
        db.addProviderServices(1,6);
        db.addProviderServices(2,2);
        db.addProviderServices(2,3);
        db.addProviderServices(2,4);
        db.addProviderServices(2,5);
        db.addProviderServices(2,6);
        db.addProviderServices(2,7);
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
        db.addProviderServices(5,5);
        db.addProviderServices(5,6);
        db.addProviderServices(5,7);
        db.addProviderServices(5,8);
        db.addProviderServices(5,9);
        db.addProviderServices(5,10);

        db.addAppointment(6,0,1,"Mar 22, 10:00am","ok","ok");
        db.addAppointment(7,0,2,"Mar 22, 2:00pm","ok","ok");
        db.addAppointment(8,0,3,"Mar 22, 5:00pm","ok","ok");
        db.addAppointment(9,0,4,"Mar 23, 10:00am","ok","ok");
        db.addAppointment(10,0,5,"Mar 23, 2:00pm","ok","ok");
        db.addAppointment(11,1,6,"Mar 23, 5:00pm","ok","ok");
        db.addAppointment(12,1,7,"Mar 24, 10:00am","ok","ok");
        db.addAppointment(13,1,8,"Mar 24, 2:00pm","ok","ok");
        db.addAppointment(14,1,9,"Mar 24, 5:00pm","ok","ok");
        db.addAppointment(15,1,10,"Mar 25, 10:00am","ok","ok");
        db.addAppointment(16,2,11,"Mar 25, 2:00pm","ok","ok");
        db.addAppointment(17,2,12,"Mar 25, 5:00pm","ok","ok");
        db.addAppointment(18,2,13,"Mar 26, 10:00am","ok","ok");
        db.addAppointment(19,2,14,"Mar 26, 2:00pm","ok","ok");
        db.addAppointment(20,2,15,"Mar 26, 5:00pm","ok","ok");
        db.addAppointment(21,3,16,"Mar 27, 10:00am","ok","ok");
        db.addAppointment(22,3,17,"Mar 27, 2:00pm","ok","ok");
        db.addAppointment(23,3,18,"Mar 27, 5:00pm","ok","ok");
        db.addAppointment(24,3,19,"Mar 28, 10:00am","ok","ok");
        db.addAppointment(25,3,20,"Mar 28, 2:00pm","ok","ok");
        db.addAppointment(26,4,21,"Mar 28, 5:00pm","ok","ok");
        db.addAppointment(27,4,22,"Mar 29, 10:00am","ok","ok");
        db.addAppointment(28,4,23,"Mar 29, 2:00pm","ok","ok");
        db.addAppointment(29,4,24,"Mar 29, 5:00pm","ok","ok");
        db.addAppointment(30,4,25,"Mar 30, 10:00am","ok","ok");
        db.addAppointment(31,5,26,"Mar 30, 2:00pm","ok","ok");
        db.addAppointment(32,5,27,"Mar 30, 5:00pm","ok","ok");
        db.addAppointment(33,5,28,"Mar 31, 10:00am","ok","ok");
        db.addAppointment(34,5,29,"Mar 31, 2:00pm","ok","ok");
        db.addAppointment(35,5,30,"Mar 31, 5:00pm","ok","ok");
        db.addAppointment(35,1,31,"Apr 1, 5:00pm","ok","ok");
        db.addAppointment(34,1,32,"Apr 2, 5:00pm","ok","ok");
        db.addAppointment(33,1,33,"Apr 3, 5:00pm","ok","ok");
        db.addAppointment(32,1,34,"Apr 4, 5:00pm","ok","ok");
        db.addAppointment(31,1,35,"Apr 5, 5:00pm","ok","ok");
        db.addAppointment(30,1,36,"Apr 6, 5:00pm","ok","ok");
        db.addAppointment(29,1,37,"Apr 7, 5:00pm","ok","ok");

    }
}