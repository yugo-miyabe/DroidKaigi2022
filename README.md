# JetpackComposeTraining
- JetpackComposeのお勉強


## Compose を使用したナビゲーション 
- NavController を作成するには、コンポーザブルで rememberNavController() メソッドを使用する

```Kotlin
val navController = rememberNavController()
```

- NavController は、これを参照する必要があるすべてのコンポーザブルがアクセスできるコンポーザブル階層内の場所に作成する必要がある

## NavHost の作成
```Kotlin
NavHost(navController = navController, startDestination = "profile") {
    composable("profile") { Profile(/*...*/) }
    composable("friendslist") { FriendsList(/*...*/) }
    /*...*/
}
```

## コンポーザブルに移動する
```Kotlin
navController.navigate("friendslist")
```
