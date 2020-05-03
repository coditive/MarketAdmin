package com.syrous.lib_bluetooth.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.syrous.lib_bluetooth.Ticket;

public class TicketPreview extends AppCompatTextView {

    private static Typeface typeface;

    public TicketPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TicketPreview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TicketPreview(Context context) {
        super(context);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        setGravity(Gravity.CENTER);
        if (isInEditMode()) {
            setTypeface(Typeface.MONOSPACE);
            setText("TicketPreview");
            return;
        }

        if (typeface == null) {
            typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/FreePixel.ttf");
        }
        setTypeface(typeface);
    }

    public void setTicket(Ticket ticket) {
//		TODO calculate text size for fit to width
        setText(ticket.getTicketPreview());
    }

}
