# chronicle-toy
Sample application suitable for having implementation separated out from controller a view processes.

Based on a market making type process which transforms input price to an output price.

- `Universe` is a hard coded product universe and static data
- `WebInterface` shows the basic start/stop parameter change commands
- `ArrayOutputServiceView` captures the output for display
- `Engine` is the main execution look which polls all the inputs