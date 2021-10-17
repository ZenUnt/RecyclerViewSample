package com.websarva.wings.android.recyclerviewsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Toolbarを取得
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        // ツールバーにロゴを設定
        toolbar.setLogo(R.mipmap.ic_launcher)
        // アクションバーにツールバーを設定
        setSupportActionBar(toolbar)
        // CollapsingToolbarLayoutを取得
        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        // タイトルを設定
        toolbarLayout.title = getString(R.string.toolbar_title)
        // 通常サイズ時の文字色を設定
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        // 縮小サイズ時の文字色を設定
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)
        // RecyclerViewを取得
        val lvMenu = findViewById<RecyclerView>(R.id.lvMenu)
        // LinearLayoutManagerオブジェクトを生成
        val layout = LinearLayoutManager(this@MainActivity)
        // RecyclerViewにレイアウトマネージャーとしてLinearLayoutを設定
        lvMenu.layoutManager = layout
        // 定食メニューリストデータを生成
        val menuList = createTeishokuList()
        // アダプタオブジェクトを生成
        val adapter = RecyclerListAdapter(menuList)
        // RecyclerViewにアダプタオブジェクトを設定
        lvMenu.adapter = adapter
        // 区切り専用のオブジェクトを生成
        val decorator = DividerItemDecoration(this@MainActivity, layout.orientation)
        // RecycleViewに区切り線オブジェクトを設定
        lvMenu.addItemDecoration(decorator)
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        // 定食メニューリスト用のListオブジェクトを作成
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        // から揚げ定食のデータを格納するMapオブジェクトの作成とリストへの登録
        var menu = mutableMapOf<String, Any>("name" to "から揚げ定食", "price" to 800, "desc" to "若鶏の唐揚にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "ハンバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "焼肉定食", "price" to 900, "desc" to "豚の焼き肉にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "焼き魚定食", "price" to 800, "desc" to "サバの焼き魚にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "とんかつ定食", "price" to 850, "desc" to "とんかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "生姜焼き定食", "price" to 800, "desc" to "生姜焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "野菜炒め定食", "price" to 700, "desc" to "野菜炒めにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String, Any>("name" to "海老フライ定食", "price" to 900, "desc" to "海老フライにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        return menuList
    }

    private inner class RecyclerListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // リスト１行文中でメニュー名を表示する画面部品
        var _tvMenuNameRow: TextView
        // リスト１行文中でメニュー金額を表示する画面部品
        var _tvMenuPriceRow: TextView

        init {
            // 引数で渡されたリスト１行分の画面部品中から表示に使われるTextViewを取得
            _tvMenuNameRow = itemView.findViewById(R.id.tvMenuNameRow)
            _tvMenuPriceRow = itemView.findViewById(R.id.tvMenuPriceRow)
        }
    }

    private inner class RecyclerListAdapter(private val _listData: MutableList<MutableMap<String, Any>>):
        RecyclerView.Adapter<RecyclerListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            // レイアウトインフレータを取得
            val inflater = LayoutInflater.from(this@MainActivity)
            // row.xmlをインフレー都市１行分の画面部品とする
            val view = inflater.inflate(R.layout.row, parent, false)
            // インフレーとされた1行分の画面部品にリスナを設定
            view.setOnClickListener(ItemClickListener())
            // ビューホルダーオブジェクトを生成
            val holder = RecyclerListViewHolder(view)
            // 生成したビューホルダーを返す
            return holder
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            // リストデータから該当１行分のデータを取得
            val item = _listData[position]
            // メニュー名文字列を取得
            val menuName = item["name"] as String
            // メニュー金額を取得
            val menuPrice = item["price"] as Int
            // 表示用に金額を文字列に返還
            val menuPriceStr = menuPrice.toString()
            // メニュー名と金額をビューホルダー中のTextViewに設定
            holder._tvMenuNameRow.text = menuName
            holder._tvMenuPriceRow.text = menuPriceStr
        }

        override fun getItemCount(): Int {
            // リストデータ中の件数を返す
            return _listData.size
        }
    }

    private inner class ItemClickListener: View.OnClickListener {
        override fun onClick(view: View) {
            // タップされたlinearLayout内にあるメニュー名表示TextViewを取得
            val tvMenuName = view.findViewById<TextView>(R.id.tvMenuNameRow)
            //メニュー名表示TextViewから表示されているメニュー名文字列を取得
            val menuName = tvMenuName.text.toString()
            // トーストに表示する文字列を生成
            val msg = getString(R.string.msg_header) + menuName
            // トースト表示
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}