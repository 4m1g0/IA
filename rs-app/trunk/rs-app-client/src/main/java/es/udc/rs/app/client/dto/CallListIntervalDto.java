package es.udc.rs.app.client.dto;

import java.net.URI;
import java.util.List;

public class CallListIntervalDto {
	private List<CallDto> calls;
	private URI nextIntervalUri;
	private URI previousIntervalUri;
	
	public CallListIntervalDto(List<CallDto> calls, URI nextIntervalUri,
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

	public List<CallDto> getCalls() {
		return calls;
	}

	public void setCalls(List<CallDto> calls) {
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
