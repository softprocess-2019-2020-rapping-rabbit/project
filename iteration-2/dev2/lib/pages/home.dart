import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart';

String strDrink = "Hello, on this page, you can receive a winning coctail";
String strDrinkThumb =
    "http://3.bp.blogspot.com/_6uF1NzP2nac/S9Q1jlrKABI/AAAAAAAAAFw/JbP3EWH32pA/s1600/cocktails.jpg";
String strInstructions = "Press the shuffle button";
String randomUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
String cocktailUrl =
    "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";

String ingr = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=";

List<String> strDrinkIngr = [""];
List<String> strIngredient = [""];
String urlIngredient = "";
String strDrinkThumbIngr = "";

fetch(String url) async {
  print("start");

  final JsonDecoder _decoder = new JsonDecoder();
  Response newResponse = await get(url);
  final String jsonBody = newResponse.body;
  final statusCode = newResponse.statusCode;

  if (statusCode < 200 || statusCode >= 300 || jsonBody == null) {
    throw Exception('Failed to load post');
  }

  final recipesContainer = _decoder.convert(jsonBody);
  final Map<String, dynamic> testMap = recipesContainer['drinks'][0];
  final List recipeItems = recipesContainer['drinks'];

  if (recipeItems == null) {
    strDrink = "NaN";
    strDrinkThumb = "";
  } else {
    strDrink = recipeItems[0]["strDrink"];
    strDrinkThumb = recipeItems[0]["strDrinkThumb"];
    strInstructions = recipeItems[0]["strInstructions"];
    strIngredient.clear();

    for (int i = 0; i < testMap.length; ++i) {
      if (testMap.keys.elementAt(i).toString().startsWith("strIngredient") &&
          testMap.values.elementAt(i).toString().isNotEmpty &&
          testMap.values.elementAt(i).toString() != "null")
        strIngredient.add(testMap.values.elementAt(i));
    }
  }

  print(testMap.toString());
}

class MyHome extends StatefulWidget {
  @override
  MyHomePage createState() => new MyHomePage();
}

class MyHomePage extends State<MyHome> {
  Widget appBarTitle = new Text("Cocktails");
  Icon actionIcon = new Icon(Icons.search);
  final myController = TextEditingController();

  List<String> _values = new List<String>();


  @override
  void dispose() {
    myController.dispose();
    super.dispose();
  }

  void initState() {
    _values.addAll(["Cocktails", "Ingredient"]);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body:Center(
          child: myLayoutWidget()) ,
    );
  }
  fetchR() async {
    print("start");
    String url = "https://www.thecocktaildb.com/api/json/v1/1/random.php";

    final JsonDecoder _decoder = new JsonDecoder();
    Response newResponse = await get(url);
    final String jsonBody = newResponse.body;
    final statusCode = newResponse.statusCode;

    if (statusCode < 200 || statusCode >= 300 || jsonBody == null) {
      throw Exception('Failed to load post');
    }

    final recipesContainer = _decoder.convert(jsonBody);
    final Map<String, dynamic> testMap = recipesContainer['drinks'][0];
    final List recipeItems = recipesContainer['drinks'];

    if (recipeItems == null) {
      strDrink = "NaN";
      strDrinkThumb = "";
    } else {
      strDrink = recipeItems[0]["strDrink"];
      strDrinkThumb = recipeItems[0]["strDrinkThumb"];
      strInstructions = recipeItems[0]["strInstructions"];
      strIngredient.clear();

      for (int i = 0; i < testMap.length; ++i) {
        if (testMap.keys.elementAt(i).toString().startsWith("strIngredient") &&
            testMap.values.elementAt(i).toString().isNotEmpty &&
            testMap.values.elementAt(i).toString() != "null")
          strIngredient.add(testMap.values.elementAt(i));
      }
    }
    setState(() => strDrink);
    print(testMap.toString());
  }
  Widget myLayoutWidget() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Text(' Name : $strDrink'),
        Image.network(strDrinkThumb),
        Text('Instruction $strInstructions'),
        FlatButton(
          child: Text('Refresh'),
          onPressed: fetchR,
        ),
      ],
    );
}

}
