import 'package:flutter/material.dart';
import 'package:ui/service/api/author_service.dart';

import '../entity/author.dart';

class AuthorListPage extends StatefulWidget {
  const AuthorListPage({super.key});

  @override
  State<AuthorListPage> createState() => _AuthorListPageState();
}

class _AuthorListPageState extends State<AuthorListPage> {
  Author _fetchedAuthor =
      const Author(name: 'test', description: 'test description');
  final _authorApiService = AuthorApiService();

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Authors'),
        ),
        body: FutureBuilder(
          future: _fetchAuthors(),
          builder: (BuildContext context, AsyncSnapshot<dynamic> snapshot) {
            switch (snapshot.connectionState) {
              case ConnectionState.waiting:
              case ConnectionState.done:
                return Column(
                  children: [
                    Card.outlined(
                      child: Column(
                        children: [
                          Text(_fetchedAuthor.name),
                          if (_fetchedAuthor.description != null)
                            Text(_fetchedAuthor.description!)
                        ],
                      ),
                    ),
                    ElevatedButton(
                        onPressed: () => _authorApiService.getAllAuthors(),
                        child: const Row(
                          children: [
                            Icon(Icons.refresh),
                            Text('Get all authors')
                          ],
                        ))
                  ],
                );
              default:
                return const CircularProgressIndicator();
            }
          },
        ));
  }

  Future<void> _fetchAuthors() async {
    final author = await _authorApiService.getAuthorById('2');
    setState(() {
      _fetchedAuthor = author;
    });
  }
}
