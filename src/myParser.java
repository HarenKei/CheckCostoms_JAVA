import java.net.URL;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import java.util.List;

public class myParser {
    public static void main(String[] args) throws IOException {
        Scanner scFile = new Scanner(new File("././key"));
        Scanner sc = new Scanner(System.in);
        String key = scFile.nextLine();
        String year;
        String hBl;

        System.out.println("<<<화물 통관 정보를 조회합니다.>>>");
        System.out.print("화물의 귀속 연도를 입력하세요 : ");
        year = sc.next();
        System.out.print("화물의 송장 번호를 입력하세요 : ");
        hBl = sc.next();

        String adress = "https://unipass.customs.go.kr:38010/ext/rest/cargCsclPrgsInfoQry/retrieveCargCsclPrgsInfo?crkyCn="
                + key + "&hblNo=" + hBl + "&blYy=" + year;

        Source source = new Source(new URL(adress));

        List<Element> els = source.getAllElements("csclPrgsStts");
        System.out.println("Elements size:" + els.size());
        for (int i = 0; i < els.size(); i++) {
            Element el = els.get(i);
            System.out.println("content:" + el.getContent());
        //6079209801262

        }
        //System.out.println(adress);
    }
}
