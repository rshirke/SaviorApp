delete from USERFB;

insert into USERFB(ID,USERNAME, EMAIL, FACEBOOKID) values
(1,"rohanshirke","r@r.com","12345"),
(2,"rohan1","r1@r.com","123456");

delete from BLOODBANK;

insert into BLOODBANK(ID, BLOODBANKID, BLOODBANKNAME, GEOX, GEOY, APLUSGROUPQTY,AMINGROUPQTY, BPLUSGROUPQTY, BMINGROUPQTY, ABPLUSGROUPQTY,ABMINGROUPQTY, OPLUSGROUPQTY,OMINGROUPQTY) values
(1,1001,"CHICAGO Blood Bank", 41.881832, -87.623177,    1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0 ),
(2,1002,"NewYork Blood Bank", 40.730610,-73.935242,     2.0,2.0,2.0,2.0,2.0,2.0,2.0,2.0 ),
(3,1003,"California Blood Bank", 36.778259,-119.417931, 3.0,3.0,3.0,3.0,3.0,3.0,3.0,3.0 )