package tahrir.io.net.microblogging;

import tahrir.TrConstants;
import tahrir.io.net.TrSession;
import tahrir.io.net.microblogging.microblogs.Microblog;
import tahrir.io.net.microblogging.microblogs.Microblog;
import tahrir.io.net.sessions.Priority;

public interface TransmitMicroblogSession extends TrSession {
	@Priority(TrConstants.MICROBLOG_BROADCAST_PRIORITY)
	public void areYouInterested(final int mbHash);

	@Priority(TrConstants.MICROBLOG_BROADCAST_PRIORITY)
	public void sendMicroblog(final Microblog mb);

	@Priority(TrConstants.MICROBLOG_BROADCAST_PRIORITY)
	public void interestIs(boolean interest);

	@Priority(TrConstants.MICROBLOG_BROADCAST_PRIORITY)
	public void sessionFinished();
}
