kubectl create deployment demo-app --image=demo-app --dry-run=client -o=yaml > deployment.yaml
kubectl create service clusterip demo-app --tcp=8080:8080 --dry-run=client -o=yaml >> service.yaml
