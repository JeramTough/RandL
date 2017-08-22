package com.jeramtough.action.component;

import com.github.cage.Cage;
import com.github.cage.image.ConstantColorGenerator;
import com.github.cage.image.EffectConfig;
import com.github.cage.image.Painter;
import com.github.cage.image.ScaleConfig;
import com.github.cage.token.RandomCharacterGeneratorFactory;
import com.github.cage.token.RandomTokenGenerator;
import com.jeramtough.util.WordUtil;
import com.jtlog.user.command.P;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Administrator
 * on 2017  August 12 Saturday 16:20.
 */
@Component
public class MCage extends com.github.cage.Cage {
	/**
	 * Height of CAPTCHA image.
	 */
	protected static final int HEIGHT = 80;
	
	/**
	 * Width of CAPTCHA image.
	 */
	protected static final int WIDTH = 290;
	
	/**
	 * Character set supplied to the {@link RandomTokenGenerator} used by this
	 * template.
	 */
	protected static final char[] TOKEN_DEFAULT_CHARACTER_SET = (new String(
			RandomCharacterGeneratorFactory.DEFAULT_DEFAULT_CHARACTER_SET)
			.replaceAll("b|f|i|j|l|m|o|t", "")
			+ new String(
			RandomCharacterGeneratorFactory.DEFAULT_DEFAULT_CHARACTER_SET)
			.replaceAll("c|i|o", "").toUpperCase(Locale.ENGLISH) + new String(
			RandomCharacterGeneratorFactory.ARABIC_NUMERALS).replaceAll(
			"0|1|9", "")).toCharArray();
	
	/**
	 * Minimum length of token.
	 */
	protected static final int TOKEN_LEN_MIN = 6;
	
	/**
	 * Maximum length of token is {@value #TOKEN_LEN_MIN} +
	 * {@value #TOKEN_LEN_DELTA}.
	 */
	protected static final int TOKEN_LEN_DELTA = 2;
	
	
	/**
	 * random text that is 4 length
	 */
	private String text;
	/**
	 * Constructor.
	 */
	public MCage() {
		this(new Random(),determineBackgroundColor(),determineFontColor());
	}
	
	/**
	 * Constructor.
	 *
	 * @param rnd
	 *            object used for random value generation. Not null.
	 */
	private MCage(Random rnd, Color backgroundColor,Color fontColor) {
		super(new Painter(WIDTH, HEIGHT,backgroundColor, null, new EffectConfig
				(true,
						true, true, false, new ScaleConfig(0.55f, 0.55f)), rnd), null,
				new ConstantColorGenerator(fontColor), null,
				Cage.DEFAULT_COMPRESS_RATIO, new RandomTokenGenerator(rnd,
						new RandomCharacterGeneratorFactory(
								TOKEN_DEFAULT_CHARACTER_SET, null, rnd),
						TOKEN_LEN_MIN, TOKEN_LEN_DELTA), rnd);
		
		//process captcha text
		text = RandomStringUtils.random(4, true, false);
	}
	
	
	public void draw(OutputStream ostream) throws IOException
	{
		try
		{
			super.draw(text, ostream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	
	}
	
	/**
	 * @return the random text for lower
	 */
	public String getLowerText()
	{
		return WordUtil.exLowerChange(text);
	}
	
	//*********************
	private static Color determineBackgroundColor()
	{
		int a=(int)(Math.random()*5);
		Color color=Color.WHITE;
		switch (a)
		{
			case 0:
				return Color.WHITE;
			case 1:
				return Color.YELLOW;
			case 2:
				return new Color(227, 209, 209);
			case 3:
				return new Color(150, 225, 235);
			case 4:
				return new Color(212, 235, 150);
		}
		return color;
	}
	
	private static Color determineFontColor()
	{
		int a=(int)(Math.random()*5);
		Color color=Color.BLACK;
		switch (a)
		{
			case 0:
				return Color.BLACK;
			case 1:
				return new Color(20, 80, 125);
			case 2:
				return new Color(125, 20, 100);
			case 3:
				return new Color(20, 125, 70);
			case 4:
				return new Color(125, 100, 20);
		}
		return color;
	}
}
