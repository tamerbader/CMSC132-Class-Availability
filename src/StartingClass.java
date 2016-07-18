import java.io.IOException;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class StartingClass {
	public static String totalText = "";
	public static Element classDiv;
	public static Elements sectionInstructor;
	public static Elements sectionClass;
	public static String url = "";

	public static void main(String[] args) {
		Document doc = new Document("My File");
		String inputValue = JOptionPane.showInputDialog("Please Type in the Class Code");
		int j = JOptionPane.showConfirmDialog(null,
				"Do you want to only see open sections?", "Do you want to only see open sections?", JOptionPane.YES_NO_OPTION);
		System.out.println("THIS IS J " + j);
		if (j == 1) {
			 url = "https://ntst.umd.edu/soc/search?courseId=" + inputValue + "&sectionId=&termId=201608&creditCompare=&credits=&courseLevelFilter=ALL&instructor=&_facetoface=on&_blended=on&_online=on&courseStartCompare=&courseStartHour=&courseStartMin=&courseStartAM=&courseEndHour=&courseEndMin=&courseEndAM=&teachingCenter=ALL&_classDay1=on&_classDay2=on&_classDay3=on&_classDay4=on&_classDay5=on";
		} else {
			 url = "https://ntst.umd.edu/soc/search?courseId=" + inputValue + "&sectionId=&termId=201608&openSectionsOnly=true&_openSectionsOnly=on&creditCompare=&credits=&courseLevelFilter=ALL&instructor=&_facetoface=on&_blended=on&_online=on&courseStartCompare=&courseStartHour=&courseStartMin=&courseStartAM=&courseEndHour=&courseEndMin=&courseEndAM=&teachingCenter=ALL&_classDay1=on&_classDay2=on&_classDay3=on&_classDay4=on&_classDay5=on";
		}
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.out.println("Invalid URL");
			e.printStackTrace();
		} 
		//JOptionPane.showMessageDialog(null, "Loading, Please Wait");
		Element classDiv = doc.getElementById(inputValue);
		if (classDiv == null) {
			System.out.println("NULL!!");
			JOptionPane.showMessageDialog(null, "This Class either has no open sections or you typed the class code wrong");
			main(null);
		} else {
		Elements sectionClass = classDiv.getElementsByClass("section-info-container");
		boolean sectionHas = classDiv.hasClass("section-instructor");
		System.out.println("HAS OR HAS NOT ");
		Elements sectionInstructor = classDiv.getElementsByClass("section-instructor");
		String classSize = "There are " + sectionClass.size() + " sections available for " + inputValue;
		totalText += classSize;
		System.out.println("There are " + sectionClass.size() + " sections available for " + inputValue);
		for (Element p : sectionClass) {
			int i = 0;
			for (Element q : sectionInstructor)
			{
				String instructorName = q.text();
				String seatCount = p.text();
				String currentText = seatCount + " open seats for " + inputValue;
				totalText = totalText + "\n" + currentText;
				System.out.println("The instructor " + instructorName + " has " + seatCount + " open seats for " + inputValue);
				i++;
				if (i == 1) {
					break;
				}

			}
		}
		JOptionPane.showMessageDialog(null, totalText);
		System.exit(0);
		}
		
	}

}
