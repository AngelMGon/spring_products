export KUBECONFIG=./kubeconfig
kubectl config view
kubectl get nodes -o wide
kubectl delete deployment rest-products --ignore-not-found=true
kubectl apply -f rest-spring.yaml
kubectl get services rest-products-lb
kubectl get pods