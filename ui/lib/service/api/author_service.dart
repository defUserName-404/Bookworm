import 'dart:convert';
import 'dart:developer';
import 'dart:io';

import 'package:ui/entity/author.dart';
import 'package:ui/service/api/api_service.dart';
import 'package:ui/util/exceptions/api_exceptions.dart';

class AuthorApiService {
  final ApiService _apiService = ApiService();
  final _uri = 'authors';

  Future<Author> getAuthorById(String id) async {
    final response = await _apiService.sendGetRequest('$_uri/$id');
    if (response.statusCode != HttpStatus.ok) {
      throw AuthorNotFoundException();
    }
    return Author.fromJson(jsonDecode(response.body));
  }

  Future<void> getAllAuthors() async {
    final response = await _apiService.sendGetRequest('$_uri/all');
    log(response.body);
    // return Author.fromJson(jsonDecode(response.body));
  }

  Future<Author> createNewAuthor(Author author) async {
    final response =
        await _apiService.sendPostRequest(_uri, jsonEncode(author));
    if (response.statusCode != HttpStatus.created) {
      throw AuthorCreationFailedException();
    }
    final decodedData = jsonDecode(response.body) as Author;
    return decodedData;
  }
}
