package ch.nc.fibu.imp.camt53;

import ch.nc.fibu.imp.BookingHandler;
import ch.nc.fibu.imp.BookingWriter;
import ch.nc.fibu.imp.camt53.BookingRules.BR_AHV;
import ch.nc.fibu.imp.camt53.BookingRules.BR_Baarbezug;
import ch.nc.fibu.imp.camt53.BookingRules.BR_Concordia;
import ch.nc.fibu.imp.camt53.BookingRules.BR_CoopMobile;
import ch.nc.fibu.imp.camt53.BookingRules.BR_Default;
import ch.nc.fibu.imp.camt53.BookingRules.BR_Lebara;
import ch.nc.fibu.imp.camt53.BookingRules.BR_Mastercard;
import ch.nc.fibu.imp.camt53.BookingRules.BR_MieteHediLangStr;
import ch.nc.fibu.imp.camt53.BookingRules.BR_PFSpesen;
import ch.nc.fibu.imp.camt53.BookingRules.BR_ParkingNeuwiesen;
import ch.nc.fibu.imp.camt53.BookingRules.BR_Pensionskasse;
import ch.nc.fibu.imp.camt53.BookingRules.BR_TwintSBB;
import ch.nc.fibu.imp.camt53.BookingRules.BR_TwintSBB_PR;
import ch.nc.fibu.imp.camt53.BookingRules.BR_Yallo;
import ch.nc.fibu.imp.jour.JournalReader;

public class Main{

   public static void main(String[] args) {

      try {
         String inputFileName = "P:\\finanzen\\2025\\PostFinance\\CHF 85-470602-0\\camt.053_P_CH3909000000854706020_1115583950_0_2025120103021291.xml";
         
         Reader reader=new Reader(inputFileName);
         JournalReader journal  = new JournalReader("P:\\finanzen\\FIBJOUR.csv");
         journal.setSelectedAccount(1010);
         journal.read();
         
         BookingWriter bookingWriter = new BookingWriter();
         bookingWriter.openOutput("P:\\finanzen\\FIBU_CSV_IMPORT.csv");
         //bookingWriter.setJournalReader(journal);
         bookingWriter.setJournalReader(journal);
         
         BookingHandler bookingHandler = new BookingHandler(bookingWriter);
         
         bookingHandler.addBookingRule(new BR_AHV());
         bookingHandler.addBookingRule(new BR_Baarbezug());
         bookingHandler.addBookingRule(new BR_Concordia());
         bookingHandler.addBookingRule(new BR_CoopMobile());
         bookingHandler.addBookingRule(new BR_Lebara());
         bookingHandler.addBookingRule(new BR_Mastercard());
         bookingHandler.addBookingRule(new BR_MieteHediLangStr());
         bookingHandler.addBookingRule(new BR_Pensionskasse());
         bookingHandler.addBookingRule(new BR_PFSpesen());
         bookingHandler.addBookingRule(new BR_TwintSBB());
         bookingHandler.addBookingRule(new BR_TwintSBB_PR());
         bookingHandler.addBookingRule(new BR_ParkingNeuwiesen());
         bookingHandler.addBookingRule(new BR_Yallo());
//         bookingHandler.addBookingRule();
//         bookingHandler.addBookingRule();
         
         bookingHandler.addBookingRule(new BR_Default());
         
         reader.read(bookingHandler);
         
         
         
//         ProcessingContext context=new ProcessingContext();
//         context.setBookingHandler(bookingHandler);
//         
//         XmlHandler xmlHandler = new XmlHandler(context);
//         
//         SAXParserFactory factory = SAXParserFactory.newInstance();
//         SAXParser saxParser = factory.newSAXParser();
//         saxParser.parse(inputFile, xmlHandler);
         
         bookingWriter.closeOutput();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }   
}


