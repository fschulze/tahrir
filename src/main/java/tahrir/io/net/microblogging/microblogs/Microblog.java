package tahrir.io.net.microblogging.microblogs;

import tahrir.TrConstants;
import tahrir.TrNode;
import tahrir.io.crypto.TrCrypto;
import tahrir.io.crypto.TrSignature;

/**
 * A microblog for broadcast.
 *
 * @author Kieran Donegan <kdonegan.92@gmail.com>
 */
public class Microblog {
	public int priority;
	public GeneralMicroblogInfo otherData;
	/**
	 * Message is the microblog in an XML format.
	 */
	public String message;
	public TrSignature signature;

	// for serialization
	public Microblog() {

	}

	public Microblog(final TrNode creatingNode, final String message) {
		this(creatingNode, message, TrConstants.BROADCAST_INIT_PRIORITY);
	}

	public Microblog(final TrNode creatingNode, final String message, final int priority) {
		// TODO: get info from config
		otherData = new GeneralMicroblogInfo("", "", creatingNode.getRemoteNodeAddress().publicKey, System.currentTimeMillis());
		this.priority = priority;
		this.message = message;
		try {
			signature = TrCrypto.sign(message, creatingNode.getPrivateNodeId().privateKey);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * For tests.
	 *
	 * @param message
	 * @param otherData
	 */
	public Microblog(String message, GeneralMicroblogInfo otherData) {
		this.message = message;
		this.otherData = otherData;
		this.signature = null;
	}

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Microblog microblog = (Microblog) o;

        if (priority != microblog.priority) return false;
        if (!message.equals(microblog.message)) return false;
        if (!otherData.equals(microblog.otherData)) return false;
        if (!signature.equals(microblog.signature)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = priority;
        result = 31 * result + otherData.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + signature.hashCode();
        return result;
    }
}