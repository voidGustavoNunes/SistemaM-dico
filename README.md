### Sistema Médico

 - Trabalho de Sistemas Distribuídos implementando um sistema médico utilizando API Java TCP

Contextualiazação:
Esta solução faz uso de Serialização e de Threads para tornar o servidor mais eficiente no atendimento das requisições dos clientes.

Do lado do cliente há uma interface gráfica para que o médico possa identificar os sintomas relatados por seus paciente. Para efeito de simplificação cada paciente poderá relatar até 10 sintomas (S0, S1, S2, ..., S9). O conjunto desses sintomas juntamente com o diagnóstico fornecido pelo médico (D1, D2, ... , D5) é que será enviado para o servidor. Exemplo 1: S3, S8, S9 e D4. Exemplo 2: S2, S3, S6, S7, S9 e D3.

O servidor se responsabiliza por armazená-los em um ArrayList. Ao servidor também poderá ser solicitada a apresentação de todos os casos armazenados por doença identificada (D1, D2, D3 etc).

Ele também se encarregará, quando solicitado, de efetuar o diagnóstico automático por meio do algoritmo apriori.

