# Python
#### 객체의 life cycle
- creation : 클래스를 정의하고 인스턴스를 생성. 이때 객체가 메모리에 할당되고 조작될 준비를 마치게 됨. (`__init__`
- handling
- destruction : 객체의 reference가 0이 될때, 더이상 참조하는 값이 없을 때 객체가 삭제됨. 객체가 필요없어졌을때 제거하는 것을 garbage collection이라 함.(`__del__`)

#### 파이썬의 GC(Garbage Collector)
객체를 reference count(객체를 참조하는 다른 객체 또는 위치가 늘어날 수 록 증감)를 통해 관리한다. Reference count가 0이 되면 객체는 메모리에서 해제가 된다.

#### call by assignment
파이썬의 경우 명시적으로 call by value나 call by reference라는 개념이 존재하지 않고 parameter로 전달받는 객체에 따라 참조방식이 결정됨.
- mutable Object (list, dict, set 등) : call by reference형식
- immutable Object (str, int, tuple 등) : call by value 형식

#### GIL(Global interpreter lock)
- 파이썬 인터프리터 자체에서 락을 전역적으로 설정해둔 것으로, 멀티 코어 프로세스에서 실행되더라도 항상 한번에 하나의 스레드만 실행할 수 있음.
- reference count를 제대로 관리하기 위해서는 모든 객체에 일일이 락을 걸어야하는 불편함이 있기때문에 특정 시점에서 하나의 스레드만 실행되도록 락을 건다.
- GIL때문에 파이썬은 스레드가 병렬적으로 동작하지 않는다. 따라서 쓰레드를 쓴다고 처리가 빨라지지 않고 오히려 느려질 수 있음.
#CS

