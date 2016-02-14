package cn.com.chinatelecom.ctpass.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class UDunKeyPair implements Parcelable {
	/** 加密类型密钥对 */
	public static int KEY_PAIR_TYPE_ENCRYPT = 1;
	/** 签名类型密钥对 */
	public static int KEY_PAIR_TYPE_SIGN = 2;

	/** 1024Bit=128Byte */
	public static int KEY_LENGTH_1024 = 1;
	/** 2048Bit=256Byte */
	public static int KEY_LENGTH_2048 = 2;

	// 返回KeyType(加密密钥还是签名密钥) ,PubKey, KeyLength(1024还是2048),Index
	/** 在卡中的Index */
	public int index;
	/** 密钥对名称 */
	public String keyPairName;
	/** 密钥对类型:加密,签名 */
	public int keyPairType;
	/** 密钥对长度:1024bit,2048bit */
	public int keyLength;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(index);
		dest.writeString(keyPairName);
		dest.writeInt(keyPairType);
		dest.writeInt(keyLength);
	}

	public static final Parcelable.Creator<UDunKeyPair> CREATOR = new Creator<UDunKeyPair>() {

		@Override
		public UDunKeyPair[] newArray(int size) {
			return new UDunKeyPair[size];
		}

		@Override
		public UDunKeyPair createFromParcel(Parcel source) {
			UDunKeyPair keyPair = new UDunKeyPair();
			keyPair.index = source.readInt();
			keyPair.keyPairName = source.readString();
			keyPair.keyPairType = source.readInt();
			keyPair.keyLength = source.readInt();
			return keyPair;
		}
	};
}
