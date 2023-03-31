package org.arep;

import java.util.ArrayList;

import static spark.Spark.port;
import static spark.Spark.get;

public class Collatz {
    public static void main(String[] args) {
        port(getPort());
        get("main", (req,res) -> {
            String n = req.queryParams("value");
            int number = 0;
            try {
                number = Integer.parseInt(n);
            }catch (Exception e){
                return "Ingrese un numero valido";
            }
            ArrayList<Integer> seq = new ArrayList<>();
            while (number > 1){
                seq.add(number);
                if (number % 2 == 0){
                    number = number/2;
                }else {
                    number = (3*number) + 1;
                }
            }
            seq.add(1);
            String response = seq.toString().replace(", ", "->").replace("[","").replace("]","");

            return "{\n" +
                    "\n" +
                    " \"operation\": \"collatzsequence\",\n" +
                    "\n" +
                    " \"input\":"+ n +",\n" +
                    "\n" +
                    " \"output\":  \""+ response +"\"\n" +
                    "\n" +
                    "}";
        });
        get("collatzsequence", (req, res) -> {
            return "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "    <head>\n" +
                    "        <title>Form Example</title>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    </head>\n" +
                    "    <body>\n" +
                    "        <h1>Form with GET</h1>\n" +
                    "        <form action=\"/hello\">\n" +
                    "            <label for=\"name\">Number:</label><br>\n" +
                    "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n" +
                    "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                    "        </form> \n" +
                    "        <div id=\"getrespmsg\"></div>\n" +
                    "\n" +
                    "        <script>\n" +
                    "            function loadGetMsg() {\n" +
                    "                let nameVar = document.getElementById(\"name\").value;\n" +
                    "                const xhttp = new XMLHttpRequest();\n" +
                    "                xhttp.onload = function() {\n" +
                    "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                    "                    this.responseText;\n" +
                    "                }\n" +
                    "                xhttp.open(\"GET\", \"/main?value=\"+nameVar);\n" +
                    "                xhttp.send();\n" +
                    "            }\n" +
                    "        </script>\n" +
                    "    </body>\n" +
                    "</html>";
        });
    }



    public static int getPort(){
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}