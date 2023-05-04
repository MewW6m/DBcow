# Introduction

はじめに

### ■ 本アプリについて <a href="#apurinitsuite" id="apurinitsuite"></a>

#### 〇 DBcowとは？ <a href="#dbcowtoha" id="dbcowtoha"></a>

* **D**ata**B**ase **C**lient **O**n **W**eb の略称。
*   いわゆるWebデータベースアプリ。Web上で、既存のDBのテーブル操作(CRUD)を行うことができる。

    > Webデータベース ... Webブラウザを用いて、データの管理（検索、参照、登録、更新）とデータベースそのもの（テーブルなど）の作成など、あらゆる操作ができるアプリケーション
* 開発のきっかけは、既存のDBに対するCRUDができるWebデータベースアプリがなかったため。
* 目的は、本アプリを通して、より簡単にデータにアクセスできるようになること。

​

#### 〇 機能 <a href="#ling-ji-neng" id="ling-ji-neng"></a>

**ユーザーごとに接続情報の管理が可能**

* ユーザーごとに、接続情報を登録し管理することが可能。
* 管理者含め、他人の接続情報を閲覧することは不可。

**データの一覧を照会・検索**

* データの一覧を表示し、検索・ソート・ページングすることが可能

**データの更新・削除**

* データの詳細を閲覧・編集・削除が可能
  * ただし、DB単位・テーブル単位の操作は不可。

​

#### 〇 特徴 <a href="#ling-te-zheng" id="ling-te-zheng"></a>

* オンプレミス・閉域網(イントラネット)で利用されることを想定している。
  * 外部への通信は必要無く、内部でのみ完結して動作する
* ホスティングサーバーのOSはWindows/Linux/Mac上で動作可能
* 接続先のDBは、OracleDB、PostgreSQL、MySQL、SQLite、Teradataは動作可能
* 日本人が作っているOSS(完全無料)。商用利用も可能。

### ■ 本ドキュメント群について概要 <a href="#dokyumentonitsuite" id="dokyumentonitsuite"></a>

* 本ページ(Gitbook)では、DBcowの各開発ドキュメントを管理している。
