package org.eclipse.gemoc.protocols.eaop.api.messages;


import org.eclipse.lsp4j.jsonrpc.messages.Either;
import org.eclipse.lsp4j.jsonrpc.messages.NotificationMessage;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;

/**
 * EAOP specific version of NotificationMessage.
 *
 * @see EaopMessageTypeAdapter
 */
public class EaopNotificationMessage extends NotificationMessage {
	/**
	 * The notification id.
	 */
	@NonNull
	private Either<String, Number> id;

	public String getId() {
		if (id == null)
			return null;
		if (id.isLeft())
			return id.getLeft();
		if (id.isRight())
			return id.getRight().toString();
		return null;
	}

	@NonNull
	public Either<String, Number> getRawId() {
		return id;
	}

	public void setId(String id) {
		this.id = Either.forLeft(id);
	}

	public void setId(int id) {
		this.id = Either.forRight(id);
	}

	public void setRawId(@NonNull Either<String, Number> id) {
		this.id = id;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		EaopNotificationMessage other = (EaopNotificationMessage) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}
}
