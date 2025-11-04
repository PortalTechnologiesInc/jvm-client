# Portal SDK - JVM Client

A Java client for the Portal WebSocket Server, providing Nostr-based authentication and Lightning Network payment processing capabilities.


## Available Commands

Commands are implemented as specific request classes in [`src/main/java/cc/getportal/command/request/`](./src/main/java/cc/getportal/command/request/), and used via the `sendCommand()` method of the [`PortalSDK`](./src/main/java/cc/getportal/PortalSDK.java) class.

Some key available commands include:

- [`AuthRequest`](./src/main/java/cc/getportal/command/request/AuthRequest.java): Authenticate using a token.
- [`KeyHandshakeUrlRequest`](./src/main/java/cc/getportal/command/request/KeyHandshakeUrlRequest.java): Get handshake URL for key and relays.
- [`RequestSinglePaymentRequest`](./src/main/java/cc/getportal/command/request/RequestSinglePaymentRequest.java): Request a single payment.
- [`MintCashuRequest`](./src/main/java/cc/getportal/command/request/MintCashuRequest.java): Mint Cashu tokens.

> See [`src/main/java/cc/getportal/command/request/`](./src/main/java/cc/getportal/command/request/) for all available commands and additional details.

To use a command, instantiate its request class and pass it to `PortalSDK.sendCommand(...)`. The full list of commands may evolve; check the request folder for the latest options.