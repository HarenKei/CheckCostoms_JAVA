import java.net.URL;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

public class myParser {
    private static String KeyLoader() throws IOException{
        Scanner scFile = new Scanner(new File("././key"));
        return scFile.nextLine();
    }
    private static String AddressMaker(String key, String hBl, String year){
        String address = "https://unipass.customs.go.kr:38010/ext/rest/cargCsclPrgs" +
                "InfoQry/retrieveCargCsclPrgsInfo?crkyCn="
                + key + "&hblNo=" + hBl + "&blYy=" + year;
        return address;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String key = KeyLoader();

        System.out.println("<<<화물 통관 정보를 조회합니다.>>>");
        System.out.print("화물의 귀속 연도를 입력하세요 : ");
        String year = sc.next();
        System.out.print("화물의 송장 번호를 입력하세요 (우체국택배 / 대한통운) : ");
        String hBl = sc.next();
        String address = AddressMaker(key, hBl, year);

        Source source = new Source(new URL(address));

        //파싱 후 출력
        System.out.println();
        System.out.println("<<<<< 송장번호 " + hBl + " 의 통관 정보입니다. >>>>>" );

        Element current = source.getFirstElement("csclPrgsStts");
        System.out.println("통관 진행 상태 : " + current.getContent());

        Element goods = source.getFirstElement("prnm");
        System.out.println("화물명 : " + goods.getContent());

        Element customs = source.getFirstElement("etprCstm");
        System.out.println("담당 세관 : " + customs.getContent());
        //6079209801262

        sc.close();

        }

    }


