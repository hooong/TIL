# K8S - 초기 설치 방법

### 실행 환경

> AWS EC2 (CentOS7) 기준
>
> Master Node 1개, Worker Node 2개 구성
>
> Docker ver.
>
> K8S ver.

<br>

## Docker Download

- [docker centos 설치 링크](https://docs.docker.com/engine/install/centos/)

  > 오픈소스 다운로드 시 공식 홈페이지를 사용하는 것이 안전.
  >
  > 블로그에 있는 레포지토리에서 받을 경우 해킹의 위험이 있을 수 있음...

- 본 실습에서는 `$ sudo su` 를 사용해 루트권한을 가지고 실행하였음.

  - 특정 유저에서 docker를 사용하려면 아래 명령어를 통해 권한을 주어야함.

    `$ sudo usermod -aG docker your-user`

<br>

## AWS 인바운드 규칙 설정

> 쿠버네티스를 사용하기 위해서는 각각의 마스터 노드와 워커 노드의 인바운드 규칙을 아래에 나오는 포트들에 대하여 허용을 해주어야한다.

- Master Node

  - 6443 : Kubernetes API Server
  - 2379 ~ 2380 : etcd server client API
  - 10250 : kubelet API
  - 10251 : kube-scheduler
  - 10252 : kube-controller-manager 

- Worker Node

  - 10250 : kubelet API
  - 30000 ~ 32767 : NodePortServices

  

## Kubernetes 설치

- 설치 Repository 설정

  ```shell
  cat <<EOF > /etc/yum.repos.d/kubernetes.repo
  [kubernetes]
  name=Kubernetes
  baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64
  enabled=1
  gpgcheck=1
  repo_gpgcheck=1
  gpgkey=https://packages.cloud.google.com/yum/doc/yum-key.gpg https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
  EOF
  ```

- SELinux Disable

  ```shell
  setenforce 0
  sed -i --follow-symlinks 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/sysconfig/selinux
  ```

- Firewall Disable

  ```shell
  systemctl stop firewalld && systemctl disable firewalld
  ```

- kubelet, kubeadm, kubectl 설치

  ```she
  sudo yum install -y kubelet kubeadm kubectl --disableexcludes=kubernetes
  ```

- kubelet 서비스 시작 및 서비스 등록

  ```shell
  sudo systemctl enable --now kubelet
  ```

- net.bridge.bridge-nf-call-iptables 셋팅

  ```shell
  cat <<EOF >  /etc/sysctl.d/k8s.conf
  net.bridge.bridge-nf-call-ip6tables = 1
  net.bridge.bridge-nf-call-iptables = 1
  net.ipv4.ip_forward = 1
  EOF
  
  sysctl --system
  ```

  - 해당 과정에 대하여 아직 자세한 이해 부족

- Swap disable

  ```shell
  swapoff -a && sed -i '/swap/s/^/#/' /etc/fstab
  ```

  - kubelet이 적절하게 작동하기 위한 설정

<br>

## Kubernetes 환경 구축 (마스터, 워커)

### Master Node

- Initializing

  ```shell
  kubeadm init
  ```

  > - root가 아니라면 아래 명령어를 통해 config 파일을 만들어야 kubectl에 명령을 내릴 수 있다.
  >
  > ```shell
  > mkdir -p $HOME/.kube 
  > sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config  
  > sudo chown $(id -u):$(id -g) $HOME/.kube/config
  > ```
  >
  > - root 유저라면 아래 환경변수를 설정하여 kubectl을 동작시킬 수 있다.
  >
  > ```shell
  > export KUBECONFIG=/etc/kubernetes/admin.conf
  > ```

- 상기 명령어가 성공적으로 수행되었다면 실행 결과는 아래와 같다.

  ```shell
  ... 생략
  
  Then you can join any number of worker nodes by running the following on each as root:
  
  kubeadm join 172.31.3.101:6443 --token ['_Token_'] \
      --discovery-token-ca-cert-hash ['_hashed Token_']
  ```

  - 이제 마스터 노드가 설정이 된 것이고 `kubeadm join 172.31.3.101:6443 --token ['_Token_'] \
        --discovery-token-ca-cert-hash ['_hashed Token_']` 해당 명령어를 통해 워커노드가 마스터 노드에 join할 수 있다.

  <br>

### Worker Node

- Master로 join하기

  ```shell
  kubeadm join 172.31.3.101:6443 --token ['_Token_'] \
      --discovery-token-ca-cert-hash ['_hashed Token_']
  ```

- 해당 명령어가 성공적으로 실행된다면 결과는 아래와 같다.

  ```shell
  ... 생략
  
  This node has joined the cluster:
  * Certificate signing request was sent to apiserver and a response was received.
  * The Kubelet was informed of the new secure connection details.
  
  Run 'kubectl get nodes' on the control-plane to see this node join the cluster.
  ```

<br>

## Master Node에서 Worker Node 확인하기

- Master Node에서 아래 명령어를 통해 연결된 Node들을 확인할 수 있다.

  `$ kubectl get nodes`

- 해당 실습에서는 Master Node 1개, Worker Node 2개로 총 3개의 Node를 확인해 볼 수 있다.

  ```shell
  NAME                                              STATUS     ROLES    AGE     VERSION
  ip-172-31-10-80.ap-northeast-2.compute.internal   NotReady   <none>   4m15s   v1.19.4
  ip-172-31-3-101.ap-northeast-2.compute.internal   NotReady   master   16m     v1.19.4
  ip-172-31-5-78.ap-northeast-2.compute.internal    NotReady   <none>   4m17s   v1.19.4
  ```

 하지만 현재 노드들의 STATUS가 'NotReady'이다. 말 그대로 동작을 하지 않고있다. 그 이유는 노드들이 같은 네트워크 인터페이스 상에 연결되지 않기 때문이다.

<br>

## Weave net

 Kubernetes에서는 CNI로 구성된 네트워크 인터페이스를 통하여 고유한 IP 주소로 통신하게 된다. 따라서, K8S는 Pod 네트워킹 인터페이스로 CNI 스펙을 준수하는 다양한 네트워크 플러그인 사용을 권장한다. 플러그인으로는 flannel, calico, weave net 등이 있으며, 해당 실습에서는 weave를 사용하고자한다. 

 Weave net은 멀티 호스트 오버레이 네트워킹 솔루션으로 Weave Bridge를 만들어 vRouter를 통해 컨테이너 간의 통신을 가능하게 해준다.

- weave net을 사용하기 위한 포트 설정

  ```
  - TCP 6783
  - UDP 6783/6784
  ```

- Weave 적용 방법

  ```shell
  kubectl apply -f "https://cloud.weave.works/k8s/net?k8s-version=$(kubectl version | base64 | tr -d '\n')"
  ```

  - 해당 명령어를 통해 weave-net pod를 생성할 수 있다.

- `$ kubectl get pods --all-namespaces` 를 통해 현재 생성되어있는 모든 노드들 중 `weave-net`이 정상 작동하고 있는지 확인한다.

  ```shell
  NAMESPACE     NAME                                                                      READY   STATUS              RESTARTS   AGE
  kube-system   coredns-f9fd979d6-4tktf                                                   0/1     ContainerCreating   0          21m
  kube-system   coredns-f9fd979d6-fgqt4                                                   0/1     ContainerCreating   0          21m
  kube-system   etcd-ip-172-31-3-101.ap-northeast-2.compute.internal                      1/1     Running             0          22m
  kube-system   kube-apiserver-ip-172-31-3-101.ap-northeast-2.compute.internal            1/1     Running             0          22m
  kube-system   kube-controller-manager-ip-172-31-3-101.ap-northeast-2.compute.internal   1/1     Running             0          22m
  kube-system   kube-proxy-gw8t6                                                          1/1     Running             0          9m34s
  kube-system   kube-proxy-jtlzj                                                          1/1     Running             0          9m36s
  kube-system   kube-proxy-xkzqh                                                          1/1     Running             0          21m
  kube-system   kube-scheduler-ip-172-31-3-101.ap-northeast-2.compute.internal            1/1     Running             0          22m
  kube-system   weave-net-lvd7r                                                           2/2     Running             0          29s
  kube-system   weave-net-rxff2                                                           2/2     Running             0          29s
  kube-system   weave-net-xwzt2                                                           2/2     Running             0          29s
  ```

- 정상 작동한다면 `$ kubectl get nodes` 로 노드들을 다시 확인해본다.

  ```shell
  NAME                                              STATUS   ROLES    AGE   VERSION
  ip-172-31-10-80.ap-northeast-2.compute.internal   Ready    <none>   21m   v1.19.4
  ip-172-31-3-101.ap-northeast-2.compute.internal   Ready    master   33m   v1.19.4
  ip-172-31-5-78.ap-northeast-2.compute.internal    Ready    <none>   21m   v1.19.4
  ```

  이제 세 노드 모두 `Ready` 상태인 것을 확인할 수 있을 것이다.





