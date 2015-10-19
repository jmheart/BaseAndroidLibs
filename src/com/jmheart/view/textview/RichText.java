package com.jmheart.view.textview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.jmheart.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 *
 * ���ı���ʾTextView
 */
public class RichText extends TextView {

    private Drawable placeHolder, errorImage;//ռλͼ������ͼ
    private OnImageClickListener onImageClickListener;//ͼƬ����ص�

    public RichText(Context context) {
        this(context, null);
    }

    public RichText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RichText);
        placeHolder = typedArray.getDrawable(R.styleable.RichText_placeHolder);
        errorImage = typedArray.getDrawable(R.styleable.RichText_errorImage);

        if (placeHolder == null) {
            placeHolder = new ColorDrawable(Color.GRAY);
        }
        if (errorImage == null) {
            errorImage = new ColorDrawable(Color.GRAY);
        }
        typedArray.recycle();
    }

    /**
     * ���ø��ı�
     *
     * @param text ���ı�
     */
    public void setRichText(String text) {
        Spanned spanned = Html.fromHtml(text, asyncImageGetter, null);
        SpannableStringBuilder spannableStringBuilder;
        if (spanned instanceof SpannableStringBuilder) {
            spannableStringBuilder = (SpannableStringBuilder) spanned;
        } else {
            spannableStringBuilder = new SpannableStringBuilder(spanned);
        }

        ImageSpan[] imageSpans = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), ImageSpan.class);
        final List<String> imageUrls = new ArrayList<String>();

        for (int i = 0, size = imageSpans.length; i < size; i++) {
            ImageSpan imageSpan = imageSpans[i];
            String imageUrl = imageSpan.getSource();
            int start = spannableStringBuilder.getSpanStart(imageSpan);
            int end = spannableStringBuilder.getSpanEnd(imageSpan);
            imageUrls.add(imageUrl);

            final int finalI = i;
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (onImageClickListener != null) {
                        onImageClickListener.imageClicked(imageUrls, finalI);
                    }
                }
            };
            ClickableSpan[] clickableSpans = spannableStringBuilder.getSpans(start, end, ClickableSpan.class);
            if (clickableSpans != null && clickableSpans.length != 0) {
                for (ClickableSpan cs : clickableSpans) {
                    spannableStringBuilder.removeSpan(cs);
                }
            }
            spannableStringBuilder.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        super.setText(spanned);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * �첽����ͼƬ��������Picasso��
     */
    private Html.ImageGetter asyncImageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            final URLDrawable urlDrawable = new URLDrawable();
            Picasso.with(getContext()).load(source).placeholder(placeHolder).error(errorImage).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Drawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
                    drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    urlDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    urlDrawable.setDrawable(drawable);
                    RichText.this.setText(getText());
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    urlDrawable.setBounds(errorDrawable.getBounds());
                    urlDrawable.setDrawable(errorDrawable);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    urlDrawable.setBounds(placeHolderDrawable.getBounds());
                    urlDrawable.setDrawable(placeHolderDrawable);
                }
            });
            return urlDrawable;
        }
    };

    public static class URLDrawable extends BitmapDrawable {
        private Drawable drawable;

        @SuppressWarnings("deprecation")
        public URLDrawable() {
            drawable = new ColorDrawable(Color.GRAY);
        }

        @Override
        public void draw(Canvas canvas) {
            if (drawable != null)
                drawable.draw(canvas);
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }
    }

    public void setPlaceHolder(Drawable placeHolder) {
        this.placeHolder = placeHolder;
    }

    public void setErrorImage(Drawable errorImage) {
        this.errorImage = errorImage;
    }

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public interface OnImageClickListener {
        /**
         * ͼƬ�������Ļص�����
         *
         * @param imageUrls ��ƪ���ı��������ȫ��ͼƬ
         * @param position  �����ͼƬ��imageUrls�е�λ��
         */
        void imageClicked(List<String> imageUrls, int position);
    }
}
