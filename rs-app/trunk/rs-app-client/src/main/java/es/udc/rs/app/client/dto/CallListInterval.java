package es.udc.rs.app.client.dto;

import java.net.URI;
import java.util.List;

public class CallListInterval {
	private List<Call> calls;
	private URI nextIntervalUri;
	private URI previousIntervalUri;
	
	public CallListInterval(List<Call> calls, URI nextIntervalUri,
			URI previousIntervalUri) {
		super();
		this.calls = calls;
		this.nextIntervalUri = nextIntervalUri;
		this.previousIntervalUri = previousIntervalUri;
	}

	@Override
	public String toString() {
		return "CallListInterval [calls=" + calls + ", nextIntervalUri="
				+ nextIntervalUri + ", previousIntervalUri="
				+ previousIntervalUri + "]";
	}

	public List<Call> getCalls() {
		return calls;
	}

	public void setCalls(List<Call> calls) {
		this.calls = calls;
	}

	public URI getNextIntervalUri() {
		return nextIntervalUri;
	}

	public void setNextIntervalUri(URI nextIntervalUri) {
		this.nextIntervalUri = nextIntervalUri;
	}

	public URI getPreviousIntervalUri() {
		return previousIntervalUri;
	}

	public void setPreviousIntervalUri(URI previousIntervalUri) {
		this.previousIntervalUri = previousIntervalUri;
	}
}
