package com.zigac.rest.creator;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class GeneratedLayout extends VerticalLayout
{
    public GeneratedLayout()
    {
        ComboBox<String> language = new ComboBox<>("Language");
        language.setItems("PHP", "GO lang", "NodeJS");
        addComponent(language);
        addComponent(new Label("TODO Generated code"));
        Label generatedCode = new Label();
        generatedCode.setContentMode(ContentMode.PREFORMATTED);

        language.addValueChangeListener(e -> generatedCode.setValue(generateCode(e.getValue())));

        addComponent(generatedCode);
    }

    private String generateCode(String language)
    {
        if (language.equals("PHP"))
            return "<?php\n"
                    + "\n"
                    + "// get the HTTP method, path and body of the request\n"
                    + "$method = $_SERVER['REQUEST_METHOD'];\n"
                    + "//$request = explode('/', trim($_SERVER['PATH_INFO'],'/'));\n"
                    + "$mapping = trim($_SERVER['PATH_INFO']);\n"
                    + "$input = json_decode(file_get_contents('php://input'),true);\n"
                    + "\n"
                    + "// connect to the mysql database\n"
                    + "$link = mysqli_connect('localhost:3307', 'root', 'pass', 'schemename');\n"
                    + "mysqli_set_charset($link,'utf8');\n"
                    + "\n"
                    + "// create SQL based on HTTP method\n"
                    + "switch ($method) {\n"
                    + "    case 'GET':\n"
                    + "        switch ($mapping) {\n"
                    + "            case '/getLastUrl':\n"
                    + "                $sql = \"SELECT url FROM oglas ORDER BY idoglas DESC LIMIT 1\";\n"
                    + "                break;\n"
                    + "            case '/getByUrl/*':\n"
                    + "                $sql = \"SELECT idoglas FROM oglas WHERE url LIKE $url\";\n"
                    + "                break;\n"
                    + "        //    case '/getByUrl/*': TODO ?requestParams\n"
                    + "        //        $sql = \"SELECT idoglas FROM oglas WHERE url LIKE $url\"; break;\n"
                    + "        } break;\n"
                    + "    case 'PUT':\n"
                    + "        switch ($mapping) {\n"
                    + "            case '/oglas':\n"
                    + "                $sql = \"UPDATE oglas SET url = ?, html = ? WHERE idoglas = (?)\";\n"
                    + "                break;\n"
                    + "        } break;\n"
                    + "    case 'POST':\n"
                    + "        switch ($mapping) {\n"
                    + "            case '/oglas':\n"
                    + "                $sql = \"INSERT INTO oglas (title, phone_number, url, date_sale, html) VALUES (?, ?, ?, ?, ?)\";\n"
                    + "                break;\n"
                    + "        } break;\n"
                    + "    case 'DELETE':\n"
                    + "        switch ($mapping) {\n"
                    + "            case '/oglas/?id,url':\n"
                    + "                $sql = \"DELETE FROM oglas where $id AND/OR $url\";\n"
                    + "                break;\n"
                    + "        } break;\n"
                    + "}\n"
                    + "\n"
                    + "// excecute SQL statement\n"
                    + "$result = mysqli_query($link,$sql);\n"
                    + "\n"
                    + "// die if SQL statement failed\n"
                    + "if (!$result) {\n"
                    + "    http_response_code(404);\n"
                    + "    die(mysqli_error());\n"
                    + "}\n"
                    + "\n"
                    + "// print results, insert id or affected row count\n"
                    + "if ($method == 'GET') {\n"
                    + "    if (!$key) echo '[';\n"
                    + "    for ($i=0;$i<mysqli_num_rows($result);$i++) {\n"
                    + "        echo ($i>0?',':'').json_encode(mysqli_fetch_object($result));\n"
                    + "    }\n"
                    + "    if (!$key) echo ']';\n"
                    + "} elseif ($method == 'POST') {\n"
                    + "    echo mysqli_insert_id($link);\n"
                    + "} else {\n"
                    + "    echo mysqli_affected_rows($link);\n"
                    + "}\n"
                    + "\n"
                    + "// close mysql connection\n"
                    + "mysqli_close($link);";
        return null;
    }
}
