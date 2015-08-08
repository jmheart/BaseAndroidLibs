package com.jmheart.adapter;

import com.jmheart.adapter.ImageLoader.Type;
import com.jmheart.base.BaseActivity;
import com.jmheart.base.BaseApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder
{
	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	private ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position)
	{
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		// setTag
		mConvertView.setTag(this);
	}

	/**
	 * �õ�һ��ViewHolder����
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position)
	{
		if (convertView == null)
		{
			return new ViewHolder(context, parent, layoutId, position);
		}
		return (ViewHolder) convertView.getTag();
	}

	public View getConvertView()
	{
		return mConvertView;
	}

	/**
	 * ͨ���ؼ���Id��ȡ���ڵĿؼ������û�������views
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId)
	{
		View view = mViews.get(viewId);
		if (view == null)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * ΪTextView�����ַ���
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setCheckText(int viewId, String text)
	{
		CheckBox view = getView(viewId);
		view.setText(text);
		return this;
	}
	/**
	 * ΪTextView�����ַ���
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text)
	{
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}
	/**
	 * ΪTextView����ɾ�����ַ���
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setDelText(int viewId, String text)
	{
		TextView view = getView(viewId);
		view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		view.setText(text);
		return this;
	}

	/**
	 * ΪImageView����ͼƬ
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int drawableId)
	{
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);

		return this;
	}

	/**
	 * ΪImageView����ͼƬ
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bm)
	{
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}
	/**
	 * ΪImageView��������ͼƬ
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageChecUrl(int viewId, String url)
	{
		ImageView view = getView(viewId);
		BaseApplication.imageLoader.displayImage(url, view, BaseApplication.options, null);
		return this;
	}
	

	/**
	 * ΪImageView����ͼƬ
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolder setImageByUrl(int viewId, String url)
	{
		ImageLoader.getInstance(3, Type.LIFO).loadImage(url,(ImageView) getView(viewId));
		return this;
	}

	public int getPosition()
	{
		return mPosition;
	}

}
