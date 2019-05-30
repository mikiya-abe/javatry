/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.awt.*;
import java.util.*;
import java.util.List;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.SecretBox;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc.
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author mikiya.abe
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        ColorBox colorBox = colorBoxList.get(0);
        BoxColor boxColor = colorBox.getColor();
        String colorName = boxColor.getColorName();
        int answer = colorName.length();
        log(answer, colorName); // also show name for visual check
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestString = "";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpace = colorBox.getSpaceList();
            for (BoxSpace space : boxSpace) {
                Object object = space.getContent();
                if (object instanceof String) {
                    longestString = (longestString.length() > object.toString().length() ? longestString : object.toString());
                }

            }
        }
        log(longestString);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        String longestString = "";
        String shortestString = "aaaaaaaaaaaaaaaaa";
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpace = colorBox.getSpaceList();
            for (BoxSpace space : boxSpace) {
                Object object = space.getContent();
                if (object instanceof String) {
                    longestString = (longestString.length() > object.toString().length() ? longestString : object.toString());
                    shortestString = (shortestString.length() > object.toString().length() ? object.toString() : shortestString);
                }
            }
        }
        log(longestString.length() - shortestString.length());
    }

    /**
     * Which value (toString() if non-string) has second-max legnth in color-boxes? (without sort)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        // TODO mikiya.abe  (2019-05-30)
        // what a mess...
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestStr = null;
        String secondLongestStr = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace space : boxSpaceList) {
                Object object = space.getContent();
                //                    Map<Integer, String> strLengthMap = new LinkedHashMap<Integer, String>();
                //                    if (object != null) {
                //                        strLengthMap.put(object.toString().length(), object.toString());
                //                        log(strLengthMap);
                //

                // first time
                if (longestStr == null) {
                    longestStr = object.toString();
                }
                // second time
                else if (secondLongestStr == null) {
                    secondLongestStr = (longestStr.length() < object.toString().length() ? longestStr : object.toString());
                }
                // later
                else {
                    // ここの場合分けちゃんとしなきゃ
                    secondLongestStr = (longestStr.length() < object.toString().length() ? longestStr : secondLongestStr);
                    longestStr = (longestStr.length() > object.toString().length() ? longestStr : object.toString());
                }
            }
        }
        //            assert (longestStr != null);
        //            log("The length of the longest strng is " + longestStr.length());
        //            log("It's " + longestStr);
        //
        //            assert (secondLongestStr != null);
        //            log("The length of the second longest string is " + secondLongestStr.length());
        //            log("It's " + secondLongestStr);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer sum = 0;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpace = colorBox.getSpaceList();
            for (BoxSpace space : boxSpace) {
                Object object = space.getContent();
                if (object instanceof String) {
                    sum += object.toString().length();
                }
            }
        }
        log("カラーボックスに入っている文字列の長さの合計は、{" + sum + "}です。");
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String longestColorName = null;
        for (ColorBox colorBox : colorBoxList) {
            String colorBoxName = colorBox.getColor().toString();
            //            log(colorBoxName);

            if (longestColorName == null) {
                longestColorName = colorBoxName;
            } else {
                longestColorName = (longestColorName.length() > colorBoxName.length() ? longestColorName : colorBoxName);
            }
        }
        // 波括弧{}をトリムして表示
        assert (longestColorName != null);
        log("カラーボックスの中で、色の名前が一番長いものは、" + longestColorName.substring(1, longestColorName.length() - 1) + "です。");
        // 一番長い名前が複数ある場合は最後に出てきたもののみ表示
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        String prefix = "Water";

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String targetStr = null;
        for (ColorBox colorBox : colorBoxList) {
            String colorBoxName = colorBox.getColor().toString();
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof String && object.toString().startsWith(prefix)) {
                    targetStr = colorBoxName;
                }
            }
        }
        // 複数対応していない
        log(targetStr != null ? targetStr.substring(1, targetStr.length() - 1) : "Not found 'Water'");
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        String suffix = "front";

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String targetStr = null;
        for (ColorBox colorBox : colorBoxList) {
            String colorBoxName = colorBox.getColor().toString();
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof String && object.toString().endsWith(suffix)) {
                    targetStr = colorBoxName;
                }
            }
        }

        // 複数対応していない
        log(targetStr == null ?
                "'front'で終わる文字列をしまっているカラーボックスの色は" + targetStr.substring(1, targetStr.length() - 1) + "です。" :
                "Not found 'front'");
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with "front" of string ending with "front" in color-boxes? <br>
     * (あなたのカラーボックスに入ってる "front" で終わる文字列で、"front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        String suffix = "front";

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer targetIndex = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof String && object.toString().endsWith(suffix)) {
                    targetIndex = object.toString().lastIndexOf(suffix);
                }
            }
        }
        assert (targetIndex != null);
        log("frontは" + targetIndex + 1 + "文字目から始まります。");
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (あなたのカラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        String targetStr = "ど";
        Boolean foundFlag = false;

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        Integer tagetIndex = null;
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof String) {
                    String objectStr = object.toString();
                    //                    log(objectStr);

                    Integer index1 = objectStr.indexOf(targetStr);
                    Integer index2 = objectStr.indexOf(targetStr, objectStr.indexOf(targetStr) + 1);
                    if (index1 < index2) {
                        log("{}文字目から始まります。", objectStr.indexOf(targetStr, objectStr.indexOf(targetStr) + 1) + 1);
                        foundFlag = Boolean.TRUE;
                    }
                }
            }
        }
        if (foundFlag == Boolean.FALSE) {
            log("Target not found");
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        String suffix = "front";
        String targetChar = null;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof String && object.toString().endsWith(suffix)) {
                    targetChar = object.toString().substring(0, 1);
                }
            }
        }
        log("カラーボックスに入ってる \"front\" で終わる文字列の最初の一文字は{}です。", targetChar);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        String prefix = "Water";
        String lastChar = null;

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof String && object.toString().startsWith(prefix)) {
                    int strLength = object.toString().length();
                    lastChar = object.toString().substring(strLength - 1);
                }
            }
        }

        if (lastChar == null) {
            log("Target not found");
        } else {
            log("カラーボックスに入ってる \"Water\" で始まる文字列の最後の一文字は、{}です。", lastChar);
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        String targetChar = "o";
        Integer targetLength = null;

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof String && object.toString().contains(targetChar)) {
                    targetLength = object.toString().replace(targetChar, "").length();
                    //                    log(object.toString().replace(targetChar, ""));
                }
            }
        }
        if (targetChar == null) {
            log("Target not found");
        } else {
            log("{}文字です", targetLength);
        }

    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        String targetStr = null;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof java.io.File) {
                    log(object.toString());
                    targetStr = object.toString().replace("/", "\\");
                }
            }
        }

        if (targetStr == null) {
            log("Target not found");
        } else {
            log(targetStr);
        }
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        int sumOfLength = 0;
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                Object object = boxSpace.getContent();
                if (object instanceof YourPrivateRoom.DevilBox) {
                    YourPrivateRoom.DevilBox devilBox = (YourPrivateRoom.DevilBox) object;
                    devilBox.wakeUp();
                    devilBox.allowMe();
                    devilBox.open();
                    try {
                        sumOfLength += devilBox.getText().length();
                        //log(devilBox.getText());
                    } catch (YourPrivateRoom.DevilBoxTextNotFoundException e) {
                        log(e.getMessage());
                    }
                }
            }
        }
        log("The length is {}", sumOfLength);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                if (boxSpace.getContent() instanceof Map) {
                    Map mapObject = (Map) boxSpace.getContent();
                    //System.out.println(mapObject.toString());
                    List<String> keyValueList = new ArrayList<>();
                    for (Object key : mapObject.keySet()) {
                        keyValueList.add(key.toString() + " = " + mapObject.get(key));
                    }
                    System.out.printf("map:{ %s }\n", String.join(" ; ", keyValueList));
                }
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public String makeValueFormated(Object value) {
        if (value instanceof Map) {
            Map mapObject = (Map) value;
            List<String> keyValueList = new ArrayList<>();
            for (Object key : mapObject.keySet()) {
                keyValueList.add(key.toString() + " = " + makeValueFormated(mapObject.get(key)));
            }
            return String.format("map: { %s }", keyValueList.toString());
        }
        return "aho";
    }
    public void test_showMap_nested() {
        // TODO mikiya.abe  (2019-05-30)
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                if (boxSpace.getContent() instanceof Map) {
                    Map mapObject = (Map) boxSpace.getContent();
                    //System.out.println(mapObject.toString());
                    List<String> keyValueList = new ArrayList<>();
                    for (Object key : mapObject.keySet()) {
                        keyValueList.add(key.toString() + " = " + makeValueFormated(mapObject.get(key)));
                    }
                    System.out.printf("map:{ %s }\n", String.join(" ; ", keyValueList));
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        // TODO mikiya.abe  (2019-05-30)
        String targetColorBoxName = "{white}";
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        for (ColorBox colorBox : colorBoxList) {
            if (targetColorBoxName.equals(colorBox.getColor().toString())) {
                List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    if (boxSpace.getContent() instanceof SecretBox) {
                        SecretBox secretBox = (SecretBox) boxSpace.getContent();
                        System.out.println(secretBox.getText());
                    }
                }
            }

        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        // TODO mikiya.abe  (2019-05-30)
    }
}
