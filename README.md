Hello! Here are some details about this Boids implementation

Features:
- Separation - boid avoids boids that are too near (togglable)
- Cohesion - boid goes in the middle of nearby boids (togglable)
- Alignment - boid follows the nearby boids' movement (togglable)
- Customizable radii
- Customizable max speed (Going too fast will cause some boids to go out of bounds. It's easily fixable, maybe I will fix that later, or maybe never)
- Pause button
- Boid count, spatial cell count, and cell border visualizations

Technical stuffs:
- The boids are under the Boid class that has a superclass of Entity. Each boid is a Boid object
- Since the Entity class is pretty much polymorph, Obstacle class can be added under Entity class, but, I scrapped that due to that introducing another issue of pathfinding algorithm. My main focus was only the boids' three rules.
- Nearby boids are detected with the circle approach, where the center is the current boid.

Java was used as the programming language, and the user interface was developed on the Swing framework.

Credits to Craig Reynolds for designing the Boids algorithm.
