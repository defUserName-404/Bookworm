import 'dart:convert';
import 'dart:developer';

import 'package:http/http.dart' as http;
import 'package:http/http.dart';

class ApiService {
  static final ApiService _shared = ApiService._sharedInstance();

  ApiService._sharedInstance();

  factory ApiService() => _shared;

  final baseUri = 'http://16.125.5.108:8080/api/v1/';

  Future<Response> sendGetRequest(String addedUri) async {
    log('$baseUri$addedUri');
    return await http.get(
      Uri.parse('$baseUri$addedUri'),
      headers: {'Content-Type': 'application/json; charset=UTF-8'},
    );
  }

  Future<Response> sendPostRequest(String uri, json) async {
    return await http.post(
      Uri.parse(uri),
      headers: {'Content-Type': 'application/json; charset=UTF-8'},
      body: jsonEncode(json),
    );
  }
}
