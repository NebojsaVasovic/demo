import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) throws IOException {
//        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = "https://google.com";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
//
//        print("\nMedia: (%d)", media.size());
//        for (Element src : media) {
//            if (src.normalName().equals("img"))
//                print(" * %s: <%s> %sx%s (%s)",
//                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
//                        trim(src.attr("alt"), 20));
//            else
//                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
//        }
//
//        print("\nImports: (%d)", imports.size());
//        for (Element link : imports) {
//            print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"), link.attr("rel"));
//        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
//            System.out.println(link.attr("abs:href"));
//            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
        String text = doc.body().text();
        System.out.println(text);
        int p = countOccurences(text, "two");
        System.out.println(p);

        try {
            System.out.println(getDomainName(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        String ime = "Neb        jsa";
        String punoIme = "Nebojsa Vasovic";
//        System.out.println(ime.startsWith(punoIme));
//        System.out.println(ime.substring(4).trim());

        Test test1 = new Test("s", "l");
        Test test2 = new Test("s", "l");

        System.out.println(test1.hashCode());
        System.out.println(test2.hashCode());

        UrlValidator urlValidator = new UrlValidator();
        String url2 = " https://www.facebook.com/sharer/sharer.php?u=https://www.gatesnotes.com/2019-Annual-Letter?WT.mc_id=00_00_00_share_fb&title=We didn’t see this coming&quote=Nine things that have surprised us and inspired us to take action.";
        try {
            if (!urlValidator.isValid(url2)) {
                System.err.println("Unesena neispravna url adresa: " + url2);
                return;
            }
            URI uri = new URI(url2);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    static int countOccurences(String str, String word)
    {
        // split the string by spaces in a
        String[] a = str.split(" ");

        // search for pattern in a
        int count = 0;
        for (String s : a) {
            // if match found increase count
            if (word.equals(s))
                count++;
        }

        return count;
    }
    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
