package common;

public class SoundManager
{
	private static Sound mainSound = null;
	public static final int MUSIC = 0;
	public static final int EFFECT = 1;
	public static boolean activeSound = true;
	public static boolean activeEffect = true;
	// Suoni Mondo

	// Musiche livello
	public static final String KAKARIKO = "Sounds/Music/main.wav";
	public static final String DESERT = "Sounds/Music/Desert.wav";
	public static final String DUNGEON_1 = "Sounds/Music/Dung_1.wav";
	public static final String DUNGEON_2 = "Sounds/Music/Dung_2.wav";
	public static final String FOREST = "Sounds/Music/Forest.wav";
	public static final String LAKE = "Sounds/Music/Lake.wav";
	public static final String VOLCANO = "Sounds/Music/Volcano.wav";
	public static final String SKY = "Sounds/Music/Sky.wav";
	public static final String SKY_BATTLE = "Sounds/Music/FinalBattle.wav";
	public static final String NETWORK_GAME = "Sounds/Music/DarkWorldWoods.wav";

	// Suoni Effetti
	public static final String SECRET_SOLVE = "Sounds/Effetcs/Secret.wav";
	public static final String MAGIC_GATE = "Sounds/Effetcs/Recorder.wav";
	public static final String ATTACK_SPHERE_CREATED = "Sounds/Effetcs/AttackSphere.wav ";
	public static final String ATTACK_SPHERE_EXPLODED = "Sounds/Effetcs/SphereExploded.wav";
	public static final String ENEMY_KILL = "Sounds/Effetcs/Kill.wav";

	// Suoni Oggetti
	public static final String HEART_CATCHED = "Sounds/Effetcs/Get_Heart.wav";
	public static final String MAGIC_SPHERE_CATCHED = "Sounds/Effetcs/Get_Item.wav";
	public static final String COIN_CATCHED = "Sounds/Effetcs/Get_Rupee.wav";
	public static final String TREASURE_OPEN = "Sounds/Effetcs/TreasureOpen.wav";
	public static final String ENTER_INTO_GATE = " ";
	public static final String TRIFORCE_PIECE_CATCHED = "Sounds/Effetcs/TriforcePiece.wav";
	public static final String FLAME_TOWER_OPEN = "Sounds/Effetcs/Candle.wav";
	public static final String BLOCK_MOVED = " ";
	public static final String BUTTON_CHANGE_STATE = " ";
	public static final String ENTER_INTO_INVISIBLE_WALL = "Sounds/Effetcs/InvisibleWall.wav";

	// Suoni personaggi
	// Hero
	public static final String HERO_ATTACK_SWORD = "Sounds/Effetcs/Sword.wav";
	public static final String HERO_WALK = " ";
	public static final String HERO_HITTED = "Sounds/Effetcs/Hit.wav";
	public static final String HERO_FALLING = "Sounds/Effetcs/Sword.wav";
	public static final String HERO_CHARGING = "Sounds/Effetcs/Charging.wav";
	public static final String HERO_SPECIAL_ATTACK = "Sounds/Effetcs/Sword_Combined.wav";
	public static final String HERO_LOW_LIFE = "Sounds/Effetcs/LowHealth.wav";
	public static final String HERO_DIED = "Sounds/Effetcs/Die.wav";

	// Warrior
	public static final String WARRIOR_ATTACK_SWORD = "Sounds/Effetcs/Sword.wav";
	public static final String WARRIOR_WALK = " ";
	public static final String WARRIOR_HITTED = "Sounds/Effetcs/Hit.wav";
	public static final String WARRIOR_SPECIAL_ATTACK = " ";

	// Wizard
	public static final String WIZARD_ATTACK_SWORD = " ";
	public static final String WIZARD_WALK = " ";
	public static final String WIZARD_HITTED = "Sounds/Effetcs/Hit.wav";
	public static final String WIZARD_SPECIAL_ATTACK = " ";

	// Skull
	public static final String SKULL_ATTACK_SWORD = " ";
	public static final String SKULL_WALK = " ";
	public static final String SKULL_HITTED = "Sounds/Effetcs/Hit.wav";
	public static final String SKULL_SPECIAL_ATTACK = " ";

	// Suoni Menu
	public static final String MAIN_MENU = "Sounds/mainthemesong.wav";
	public static final String MENU_ENTER_BUTTON = "Sounds/Enter.wav";
	public static final String MENU_CHANGE_BUTTON = "Sounds/Enter.wav";
	public static final String MENU_CLICK_BUTTON = "Sounds/Click.wav";
	public static final String MENU_BACK_BUTTON = "Sounds/back.wav";
	public static final String CREDIT = "Sounds/zelda.wav";

	// Suoni Editor

	public static void playSound(String urlSound, int numberPlay, int typeSound)
	{
		if (activeSound && (typeSound == MUSIC))
		{
			if (mainSound != null)
			{
				mainSound.stop();
			}
			mainSound = new Sound(urlSound, true);
			mainSound.play(numberPlay);
		}

		if (activeEffect && (typeSound == EFFECT))
		{
			new Sound(urlSound, true).play(numberPlay);
		}
	}

	public static boolean stop()
	{
		if (mainSound != null)
		{
			mainSound.stop();
			return true;
		}
		return false;
	}
}
