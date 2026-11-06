Hello! Here are some details about this Boids implementation

Features:
- Separation - boid avoids boids that are too close (togglable)
- Cohesion - boid goes in the middle of nearby boids (togglable)
- Alignment - boid follows the nearby boids' movement (togglable)
- Customizable radii
- Customizable max speed
- Pause button
- Boid count, spatial cell count, and spatial cell border visualizations

---------------------------------------------------------------------------------------------------------------
Technical stuffs:
- The boids are under the Boid class that has a superclass of Entity class. Each boid is a Boid object
  
- Since the Entity class is pretty much polymorph, Obstacle class can be added under Entity class, but, I scrapped that due to that introducing another issue of pathfinding algorithm. My main focus was only the boids' three rules.
  
- Nearby boids are detected with the circle approach, where the center is the current boid. Implemented using a spatial hashmap, where spatial cells are created, so that the current boid only scans the eight spatial cells around itself, which is where scanned nearby boids are calculated to achieve the circle's narrow phase hit detection.
  
- The maximum value accepted for maxSpeed is 500. Boids can no longer stay out of bounds, so why not practically infinite? 500 is the sweet spot for the matrix's bounds, if the limit was too high, boids would continue to create new spatial cells. Anywhere above 100 is impractical anyway.

---------------------------------------------------------------------------------------------------------------
Java was used as the programming language, and the graphical user interface was developed on the Swing framework.

Credits to Craig Reynolds for designing the Boids algorithm.
