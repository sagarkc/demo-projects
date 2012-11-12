/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class Tiger extends Animal {

	/**
	 * 
	 */
	public Tiger() {
		this("Tiger", 0);
	}

	public Tiger(String name, int number) {
		super(name, number);
		setSymbol('T');
		setCanCapture(true);
	}

	public void moveTo(Box from, Box to) {
		
	}

	public void moveTo(Box to) {
		
	}
	
	public boolean canCaptureAnyGoat(Box box){
		if(box.getTopBox() != null && !box.getTopBox().isEmpty()){
			Stack<Animal> animals = box.getTopBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopBox().getTopBox() && box.getTopBox().getTopBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getTopBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopBox().getTopBox() && box.getTopBox().getTopBox().isEmpty()){
					return true;
				}
			}
		}
		
		if(box.getTopLeftBox() != null && !box.getTopLeftBox().isEmpty()){
			Stack<Animal> animals = box.getTopLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopLeftBox().getTopLeftBox() && box.getTopLeftBox().getTopLeftBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getTopLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopLeftBox().getTopLeftBox() && box.getTopLeftBox().getTopLeftBox().isEmpty()){
					return true;
				}
			}
		}
		
		if(box.getLeftBox() != null && !box.getLeftBox().isEmpty()){
			Stack<Animal> animals = box.getLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getLeftBox().getLeftBox() && box.getLeftBox().getLeftBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getLeftBox().getLeftBox() && box.getLeftBox().getLeftBox().isEmpty()){
					return true;
				}
			}
		}
		
		if(box.getBottomLeftBox() != null && !box.getBottomLeftBox().isEmpty()){
			Stack<Animal> animals = box.getBottomLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomLeftBox().getBottomLeftBox() && box.getBottomLeftBox().getBottomLeftBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getBottomLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomLeftBox().getBottomLeftBox() && box.getBottomLeftBox().getBottomLeftBox().isEmpty()){
					return true;
				}
			}
		}
		
		if(box.getBottomBox() != null && !box.getBottomBox().isEmpty()){
			Stack<Animal> animals = box.getBottomBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomBox().getBottomBox() && box.getBottomBox().getBottomBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getBottomBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomBox().getBottomBox() && box.getBottomBox().getBottomBox().isEmpty()){
					return true;
				}
			}
		}
		
		if(box.getBottomRightBox() != null && !box.getBottomRightBox().isEmpty()){
			Stack<Animal> animals = box.getBottomRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomRightBox().getBottomRightBox() && box.getBottomRightBox().getBottomRightBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getBottomRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomRightBox().getBottomRightBox() && box.getBottomRightBox().getBottomRightBox().isEmpty()){
					return true;
				}
			}
		}
		
		if(box.getRightBox() != null && !box.getRightBox().isEmpty()){
			Stack<Animal> animals = box.getRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getRightBox().getRightBox() && box.getRightBox().getRightBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getRightBox().getRightBox() && box.getRightBox().getRightBox().isEmpty()){
					return true;
				}
			}
		}
		
		if(box.getTopRightBox() != null && !box.getTopRightBox().isEmpty()){
			Stack<Animal> animals = box.getTopRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopRightBox().getTopRightBox() && box.getTopRightBox().getTopRightBox().isEmpty()){
					return true;
				}
			}
			Animal animal = box.getTopRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopRightBox().getTopRightBox() && box.getTopRightBox().getTopRightBox().isEmpty()){
					return true;
				}
			}
		}
		
		return true;
	}
	
	public Map<Animal, Box> getAllCaptureableGoats(Box box){
		Map<Animal, Box> captureableGoats = new HashMap<Animal, Box>();
		if(box.getTopBox() != null && !box.getTopBox().isEmpty()){
			Stack<Animal> animals = box.getTopBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopBox().getTopBox() && box.getTopBox().getTopBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getTopBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopBox().getTopBox() && box.getTopBox().getTopBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		if(box.getTopLeftBox() != null && !box.getTopLeftBox().isEmpty()){
			Stack<Animal> animals = box.getTopLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopLeftBox().getTopLeftBox() && box.getTopLeftBox().getTopLeftBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getTopLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopLeftBox().getTopLeftBox() && box.getTopLeftBox().getTopLeftBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		if(box.getLeftBox() != null && !box.getLeftBox().isEmpty()){
			Stack<Animal> animals = box.getLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getLeftBox().getLeftBox() && box.getLeftBox().getLeftBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getLeftBox().getLeftBox() && box.getLeftBox().getLeftBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		if(box.getBottomLeftBox() != null && !box.getBottomLeftBox().isEmpty()){
			Stack<Animal> animals = box.getBottomLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomLeftBox().getBottomLeftBox() && box.getBottomLeftBox().getBottomLeftBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getBottomLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomLeftBox().getBottomLeftBox() && box.getBottomLeftBox().getBottomLeftBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		if(box.getBottomBox() != null && !box.getBottomBox().isEmpty()){
			Stack<Animal> animals = box.getBottomBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomBox().getBottomBox() && box.getBottomBox().getBottomBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getBottomBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomBox().getBottomBox() && box.getBottomBox().getBottomBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		if(box.getBottomRightBox() != null && !box.getBottomRightBox().isEmpty()){
			Stack<Animal> animals = box.getBottomRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomRightBox().getBottomRightBox() && box.getBottomRightBox().getBottomRightBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getBottomRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomRightBox().getBottomRightBox() && box.getBottomRightBox().getBottomRightBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		if(box.getRightBox() != null && !box.getRightBox().isEmpty()){
			Stack<Animal> animals = box.getRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getRightBox().getRightBox() && box.getRightBox().getRightBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getRightBox().getRightBox() && box.getRightBox().getRightBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		if(box.getTopRightBox() != null && !box.getTopRightBox().isEmpty()){
			Stack<Animal> animals = box.getTopRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopRightBox().getTopRightBox() && box.getTopRightBox().getTopRightBox().isEmpty()){
					captureableGoats.put(animals.peek(), box);
				}
			}
			Animal animal = box.getTopRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopRightBox().getTopRightBox() && box.getTopRightBox().getTopRightBox().isEmpty()){
					captureableGoats.put(animal, box);
				}
			}
		}
		
		return captureableGoats;
	}

	/**
	 * @param box
	 * @param lastMove
	 * @return
	 */
	public boolean hasCaptureAnyGoat(Box box, PathOfMove lastMove) {
		if(box.getTopBox() != null && !box.getTopBox().isEmpty()){
			Stack<Animal> animals = box.getTopBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopBox().getTopBox() 
						&& box.getTopBox().getTopBox().isEmpty()
						&& !box.getTopBox().getTopBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getTopBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopBox().getTopBox() && box.getTopBox().getTopBox().isEmpty()
						&& !box.getTopBox().getTopBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		if(box.getTopLeftBox() != null && !box.getTopLeftBox().isEmpty()){
			Stack<Animal> animals = box.getTopLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopLeftBox().getTopLeftBox() && box.getTopLeftBox().getTopLeftBox().isEmpty()
						&& !box.getTopLeftBox().getTopLeftBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getTopLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopLeftBox().getTopLeftBox() && box.getTopLeftBox().getTopLeftBox().isEmpty()
						&& !box.getTopLeftBox().getTopLeftBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		if(box.getLeftBox() != null && !box.getLeftBox().isEmpty()){
			Stack<Animal> animals = box.getLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getLeftBox().getLeftBox() 
						&& box.getLeftBox().getLeftBox().isEmpty()
						&& !box.getLeftBox().getLeftBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getLeftBox().getLeftBox() 
						&& box.getLeftBox().getLeftBox().isEmpty()
						&& !box.getLeftBox().getLeftBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		if(box.getBottomLeftBox() != null && !box.getBottomLeftBox().isEmpty()){
			Stack<Animal> animals = box.getBottomLeftBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomLeftBox().getBottomLeftBox() 
						&& box.getBottomLeftBox().getBottomLeftBox().isEmpty()
						&& !box.getBottomLeftBox().getBottomLeftBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getBottomLeftBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomLeftBox().getBottomLeftBox() 
						&& box.getBottomLeftBox().getBottomLeftBox().isEmpty()
						&& !box.getBottomLeftBox().getBottomLeftBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		if(box.getBottomBox() != null && !box.getBottomBox().isEmpty()){
			Stack<Animal> animals = box.getBottomBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomBox().getBottomBox() 
						&& box.getBottomBox().getBottomBox().isEmpty()
						&& !box.getBottomBox().getBottomBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getBottomBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomBox().getBottomBox() 
						&& box.getBottomBox().getBottomBox().isEmpty()
						&& !box.getBottomBox().getBottomBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		if(box.getBottomRightBox() != null && !box.getBottomRightBox().isEmpty()){
			Stack<Animal> animals = box.getBottomRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getBottomRightBox().getBottomRightBox() 
						&& box.getBottomRightBox().getBottomRightBox().isEmpty()
						&& !box.getBottomRightBox().getBottomRightBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getBottomRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getBottomRightBox().getBottomRightBox() 
						&& box.getBottomRightBox().getBottomRightBox().isEmpty()
						&& !box.getBottomRightBox().getBottomRightBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		if(box.getRightBox() != null && !box.getRightBox().isEmpty()){
			Stack<Animal> animals = box.getRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getRightBox().getRightBox() 
						&& box.getRightBox().getRightBox().isEmpty()
						&& !box.getRightBox().getRightBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getRightBox().getRightBox() 
						&& box.getRightBox().getRightBox().isEmpty()
						&& !box.getRightBox().getRightBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		if(box.getTopRightBox() != null && !box.getTopRightBox().isEmpty()){
			Stack<Animal> animals = box.getTopRightBox().getAnimals();
			if(null != animals && animals.size() > 0){
				if(null != box.getTopRightBox().getTopRightBox() 
						&& box.getTopRightBox().getTopRightBox().isEmpty()
						&& !box.getTopRightBox().getTopRightBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
			Animal animal = box.getTopRightBox().getAnimal();
			if(null != animal && animal instanceof Goat){
				if(null != box.getTopRightBox().getTopRightBox() 
						&& box.getTopRightBox().getTopRightBox().isEmpty()
						&& !box.getTopRightBox().getTopRightBox().equals(lastMove.getMovedFromBox())){
					return true;
				}
			}
		}
		
		return false;
	}
	
}
