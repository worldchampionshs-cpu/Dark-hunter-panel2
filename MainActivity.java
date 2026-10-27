package com.darkhunter.panel;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final int BG = Color.rgb(10, 10, 14);
    private final int PANEL = Color.rgb(24, 20, 24);
    private final int RED = Color.rgb(255, 48, 66);
    private final int WHITE = Color.rgb(246, 242, 245);
    private final int MUTED = Color.rgb(167, 155, 163);
    private LinearLayout root;
    private boolean loggedIn = false;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(BG);
        getWindow().setNavigationBarColor(BG);
        showLogin();
    }

    private GradientDrawable shape(int color, int strokeColor, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(dp(radius));
        if (strokeColor != 0) d.setStroke(dp(1), strokeColor);
        return d;
    }

    private int dp(float value) {
        return (int)(value * getResources().getDisplayMetrics().density + 0.5f);
    }

    private LinearLayout column() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        return l;
    }

    private LinearLayout row() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);
        l.setGravity(Gravity.CENTER_VERTICAL);
        return l;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private void base(String title) {
        root = column();
        root.setBackgroundColor(BG);
        root.setPadding(dp(20), dp(18), dp(20), dp(18));
        setContentView(root);
    }

    private void showLogin() {
        base("login");
        TextView mark = text("DH", 28, WHITE, true);
        mark.setGravity(Gravity.CENTER);
        mark.setBackground(shape(RED, 0, 18));
        LinearLayout.LayoutParams markP = new LinearLayout.LayoutParams(dp(76), dp(76));
        markP.gravity = Gravity.CENTER_HORIZONTAL;
        markP.topMargin = dp(50);
        root.addView(mark, markP);

        TextView title = text("DARK HUNTER", 27, WHITE, true);
        title.setGravity(Gravity.CENTER);
        title.setLetterSpacing(0.08f);
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(-1, dp(52));
        tp.topMargin = dp(16);
        root.addView(title, tp);

        TextView subtitle = text("GAMING CONTROL CENTER", 12, RED, true);
        subtitle.setGravity(Gravity.CENTER);
        root.addView(subtitle, new LinearLayout.LayoutParams(-1, dp(28)));

        LinearLayout card = column();
        card.setPadding(dp(18), dp(20), dp(18), dp(20));
        card.setBackground(shape(PANEL, Color.rgb(67, 36, 43), 18));
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2);
        cp.topMargin = dp(38);
        root.addView(card, cp);

        card.addView(text("WELCOME BACK", 19, WHITE, true));
        TextView helper = text("Sign in to open your personal dashboard.", 13, MUTED, false);
        LinearLayout.LayoutParams hp = new LinearLayout.LayoutParams(-1, -2);
        hp.topMargin = dp(6);
        card.addView(helper, hp);

        EditText username = field("Username");
        EditText password = field("Password");
        password.setInputType(129);
        LinearLayout.LayoutParams fp = new LinearLayout.LayoutParams(-1, dp(50));
        fp.topMargin = dp(18);
        card.addView(username, fp);
        LinearLayout.LayoutParams f2 = new LinearLayout.LayoutParams(-1, dp(50));
        f2.topMargin = dp(12);
        card.addView(password, f2);

        Button login = button("ENTER PANEL");
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, dp(50));
        bp.topMargin = dp(20);
        card.addView(login, bp);
        login.setOnClickListener(v -> {
            if (username.getText().toString().trim().isEmpty() ||
                password.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter any username and password for this local demo.", Toast.LENGTH_LONG).show();
            } else {
                loggedIn = true;
                showDashboard(username.getText().toString().trim());
            }
        });

        TextView note = text("LOCAL DEMO LOGIN • NO ACCOUNT OR INTERNET REQUIRED", 10, MUTED, false);
        note.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-1, dp(44));
        np.topMargin = dp(18);
        root.addView(note, np);
    }

    private EditText field(String hint) {
        EditText e = new EditText(this);
        e.setSingleLine(true);
        e.setTextSize(14);
        e.setTextColor(WHITE);
        e.setHintTextColor(MUTED);
        e.setHint(hint);
        e.setPadding(dp(14), 0, dp(14), 0);
        e.setBackground(shape(Color.rgb(14, 13, 17), Color.rgb(67, 47, 54), 12));
        return e;
    }

    private Button button(String label) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextColor(WHITE);
        b.setTextSize(13);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setAllCaps(false);
        b.setBackground(shape(RED, 0, 12));
        return b;
    }

    private void showDashboard(String username) {
        base("dashboard");
        ScrollView scroll = new ScrollView(this);
        LinearLayout content = column();
        content.setPadding(0, 0, 0, dp(14));
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout header = row();
        TextView logo = text("DH", 18, WHITE, true);
        logo.setGravity(Gravity.CENTER);
        logo.setBackground(shape(RED, 0, 12));
        header.addView(logo, new LinearLayout.LayoutParams(dp(48), dp(48)));
        LinearLayout titleCol = column();
        titleCol.setPadding(dp(12), 0, 0, 0);
        titleCol.addView(text("DARK HUNTER", 19, WHITE, true));
        titleCol.addView(text("GAMING CONTROL CENTER", 10, RED, true));
        header.addView(titleCol, new LinearLayout.LayoutParams(0, -2, 1));
        TextView status = text("● ONLINE", 10, Color.rgb(104, 235, 150), true);
        header.addView(status);
        content.addView(header);

        View divider = new View(this);
        divider.setBackgroundColor(Color.rgb(64, 36, 43));
        LinearLayout.LayoutParams dpv = new LinearLayout.LayoutParams(-1, dp(1));
        dpv.topMargin = dp(18);
        dpv.bottomMargin = dp(18);
        content.addView(divider, dpv);

        content.addView(text("WELCOME, " + username.toUpperCase(), 21, WHITE, true));
        content.addView(text("Your gaming space. Train, customize, and improve.", 13, MUTED, false));

        LinearLayout floating = column();
        floating.setPadding(dp(16), dp(14), dp(16), dp(14));
        floating.setBackground(shape(Color.rgb(30, 19, 23), RED, 16));
        LinearLayout.LayoutParams flp = new LinearLayout.LayoutParams(-1, -2);
        flp.topMargin = dp(22);
        content.addView(floating, flp);
        LinearLayout dragBar = row();
        TextView dragTitle = text("☰  DRAGGABLE QUICK PANEL", 12, RED, true);
        dragBar.addView(dragTitle, new LinearLayout.LayoutParams(0, dp(34), 1));
        TextView grip = text("MOVE", 10, MUTED, true);
        dragBar.addView(grip);
        floating.addView(dragBar);
        floating.addView(text("Drag this panel by its header.", 12, MUTED, false));
        addDrag(floating, dragBar);

        content.addView(text("QUICK TOOLS", 14, WHITE, true), params(0, dp(48), 0, 14));
        addTool(content, "🎯  Aim Trainer", "Practice tracking and reaction time.", "Aim trainer selected — demo only.");
        addTool(content, "⚙  HUD Layout Guide", "Plan comfortable on-screen controls.", "HUD guide opened — demo panel.");
        addTool(content, "◈  Performance Tips", "View general device optimization tips.", "Close unused apps and keep your device cool.");
        addTool(content, "✦  Profile & Settings", "Change your local panel preferences.", "Settings are a visual demo.");

        Button logout = button("LOG OUT");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(48));
        lp.topMargin = dp(18);
        content.addView(logout, lp);
        logout.setOnClickListener(v -> { loggedIn = false; showLogin(); });

        TextView footer = text("DARK HUNTER • VISUAL DEMO ONLY", 10, MUTED, false);
        footer.setGravity(Gravity.CENTER);
        root.addView(footer, new LinearLayout.LayoutParams(-1, dp(34)));
    }

    private LinearLayout.LayoutParams params(int w, int h, int top, int bottom) {
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(w == 0 ? -1 : w, h);
        p.topMargin = dp(top);
        p.bottomMargin = dp(bottom);
        return p;
    }

    private void addTool(LinearLayout parent, String title, String desc, String message) {
        LinearLayout card = column();
        card.setPadding(dp(15), dp(12), dp(15), dp(12));
        card.setBackground(shape(PANEL, Color.rgb(57, 39, 45), 14));
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2);
        cp.topMargin = dp(10);
        parent.addView(card, cp);
        card.addView(text(title, 16, WHITE, true));
        TextView description = text(desc, 12, MUTED, false);
        LinearLayout.LayoutParams d = new LinearLayout.LayoutParams(-1, -2);
        d.topMargin = dp(4);
        card.addView(description, d);
        Button open = button("OPEN");
        LinearLayout.LayoutParams op = new LinearLayout.LayoutParams(-1, dp(40));
        op.topMargin = dp(10);
        card.addView(open, op);
        open.setOnClickListener(v -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
    }

    private void addDrag(View target, View handle) {
        handle.setOnTouchListener(new View.OnTouchListener() {
            float downX, downY, startX, startY;
            @Override public boolean onTouch(View v, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = e.getRawX(); downY = e.getRawY();
                        startX = target.getTranslationX(); startY = target.getTranslationY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        target.setTranslationX(startX + e.getRawX() - downX);
                        target.setTranslationY(startY + e.getRawY() - downY);
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                }
                return false;
            }
        });
    }
}
