package com.giftube.keyboard

import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class GifTubeKeyboardService : InputMethodService(), KeyboardView.OnKeyboardActionListener {

    private lateinit var keyboardView: KeyboardView
    private lateinit var mainLayout: View
    private lateinit var emojiPanel: LinearLayout
    private lateinit var gifPanel: LinearLayout
    private lateinit var emojiRecycler: RecyclerView
    private lateinit var gifRecycler: RecyclerView
    private lateinit var gifSearch: EditText

    private var turkishQKeyboard: Keyboard? = null
    private var symbolsKeyboard: Keyboard? = null

    override fun onCreate() {
        super.onCreate()
        turkishQKeyboard = Keyboard(this, R.xml.turkish_q_keyboard)
        symbolsKeyboard = Keyboard(this, R.xml.symbols_keyboard) // Optional symbols layout
    }

    override fun onCreateInputView(): View {
        mainLayout = layoutInflater.inflate(R.layout.keyboard_main, null)
        keyboardView = mainLayout.findViewById(R.id.keyboard_view)
        emojiPanel = mainLayout.findViewById(R.id.emoji_panel)
        gifPanel = mainLayout.findViewById(R.id.gif_panel)
        emojiRecycler = mainLayout.findViewById(R.id.emoji_recycler)
        gifRecycler = mainLayout.findViewById(R.id.gif_recycler)
        gifSearch = mainLayout.findViewById(R.id.gif_search)

        // Setup Keyboard
        keyboardView.keyboard = turkishQKeyboard
        keyboardView.setOnKeyboardActionListener(this)

        // Setup Tabs
        setupTabs()

        // Setup Emoji Recycler
        setupEmojiRecycler()

        // Setup GIF Recycler
        setupGifRecycler()

        return mainLayout
    }

    private fun setupTabs() {
        mainLayout.findViewById<Button>(R.id.btn_keyboard).setOnClickListener {
            showKeyboardPanel()
        }
        mainLayout.findViewById<Button>(R.id.btn_emoji).setOnClickListener {
            showEmojiPanel()
        }
        mainLayout.findViewById<Button>(R.id.btn_gif).setOnClickListener {
            showGifPanel()
        }
    }

    private fun showKeyboardPanel() {
        keyboardView.visibility = View.VISIBLE
        emojiPanel.visibility = View.GONE
        gifPanel.visibility = View.GONE
    }

    private fun showEmojiPanel() {
        keyboardView.visibility = View.GONE
        emojiPanel.visibility = View.VISIBLE
        gifPanel.visibility = View.GONE
        // Load emojis into recycler
    }

    private fun showGifPanel() {
        keyboardView.visibility = View.GONE
        emojiPanel.visibility = View.GONE
        gifPanel.visibility = View.VISIBLE
        // Initialize GIF search/API integration
    }

    private fun setupEmojiRecycler() {
        emojiRecycler.layoutManager = GridLayoutManager(this, 8)
        // EmojiAdapter implementation here
    }

    private fun setupGifRecycler() {
        gifRecycler.layoutManager = GridLayoutManager(this, 3)
        // GifAdapter implementation here
        gifSearch.setOnClickListener {
            // Trigger GIPHY/Tenor API search
        }
    }

    // --- KeyboardView.OnKeyboardActionListener ---

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val ic = currentInputConnection
        val capsOn = ic?.capsLock ?? false

        when (primaryCode) {
            -3 -> { // Shift
                // Handle shift/caps lock logic
            }
            -5 -> { // Backspace
                ic?.deleteSurroundingText(1, 0)
            }
            10 -> { // Enter
                ic?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER))
                ic?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER))
            }
            32 -> { // Space
                ic?.commitText(" ", 1)
            }
            else -> {
                val char = primaryCode.toChar()
                ic?.commitText(char.toString(), 1)
            }
        }
    }

    override fun onPress(primaryCode: Int) {}
    override fun onRelease(primaryCode: Int) {}
    override fun onText(text: CharSequence) {
        currentInputConnection?.commitText(text, 1)
    }
    override fun swipeLeft() {}
    override fun swipeRight() {}
    override fun swipeDown() {}
    override fun swipeUp() {}

    // --- GIF Integration (commitContent) ---

    fun insertGif(gifUri: String, mimeType: String) {
        val ic = currentInputConnection
        val bundle = Bundle()
        bundle.putString(InputConnection.INPUT_CONTENT_GRANT_READ_URI_PERMISSION, "1")
        
        ic?.commitContent(
            InputConnection.InputContentInfoCompat(
                android.net.Uri.parse(gifUri),
                android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N ? 
                    android.content.ContentResolver.commitContent(gifUri, mimeType, bundle) : false,
                null
            ),
            EditorInfo.IME_FLAG_NO_FULLSCREEN,
            bundle
        )
    }

    override fun onStartInputView(editorInfo: EditorInfo, restarting: Boolean) {
        super.onStartInputView(editorInfo, restarting)
        showKeyboardPanel()
    }

    override fun onFinishInputView() {
        super.onFinishInputView()
        keyboardView.keyboard = null
    }
}
