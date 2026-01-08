package ch.nc.fibu.imp.mm.receipt;

import java.util.ArrayList;

import ch.nc.fibu.imp.Booking;
import ch.nc.fibu.imp.BookingWriter;
import ch.nc.fibu.imp.mm.item.ItemHandler;

public class Accounting {

	private ItemHandler itemHandler;
	private final int accountListSize = 5;
	private Account[] accountList;
	private BookingWriter bookingWriter;

	public void setItemHandler(ItemHandler itemHandler) {
		this.itemHandler = itemHandler;
	}

	public void setBookingWriter(BookingWriter bookingWriter) {
		this.bookingWriter = bookingWriter;
	}

	public void processReceipt(Receipt receipt) {
		Booking booking;
		itemHandler.setDate(receipt.getDate());
		if (!receipt.isRestaurant()) {
			ArrayList<Article> articleList = receipt.getItemList();
			accountList = new Account[accountListSize];
			for (Article article : articleList) {
				Integer accountNr = itemHandler.getAccout(article.name);
				if (accountNr != null) {
					addArticleToAccount(accountNr, article);
				}
			}
			for (Account account : accountList) {
				if (account != null) {
					booking = new Booking();
					booking.setDate(receipt.getDate());
					booking.setBetrag(account.ammount);
					booking.setSoll(account.accountNr);
					booking.setHaben(9981);
					booking.setText(receipt.getFiliale());
					bookingWriter.addBooking(booking);
				}
			}
			System.out.println(toString());
			
		} else {
			booking = new Booking();
			booking.setDate(receipt.getDate());
			booking.setBetrag(receipt.getTotal());
			booking.setSoll(5810);
			booking.setHaben(9981);
			booking.setText(receipt.getFiliale() + ", Konsumatiom im Restaurant");
			bookingWriter.addBooking(booking);
		}

		

	}

	private void addArticleToAccount(int accountNr, Article article) {
		Account account;
		for (int i = 0; i < accountListSize; i++) {
			account = accountList[i];
			if (account == null) { // we reached the end of available accounts
				account = new Account();
				account.accountNr = accountNr;
				account.ammount = article.price;
				account.nrOfActicle = 1;
				accountList[i] = account;
				break;
			} else if (account.accountNr == accountNr) {
				account.ammount = account.ammount + article.price;
				account.nrOfActicle++;
				break;
			}
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Account List: ");
		for (Account account : accountList) {
			if (account != null) {
				sb.append(account.accountNr + ", ");
				sb.append(account.ammount + ", ");
				sb.append(account.nrOfActicle + "/ ");
			}
		}
		return sb.toString();
	}

	class Account {
		int accountNr;
		float ammount;
		int nrOfActicle;
	}
}
