#include<stdio.h>
#include<stdlib.h>


struct TreeNode 
{

	int data;

	struct TreeNode* left;
	struct TreeNode* right;

}	*root;

struct StackNode 
{

	struct TreeNode* node;

	struct StackNode* below;

} *topOdd,*topEven;


struct TreeNode* PopEven() 
{
	struct StackNode* temp = topEven;

	
	topEven = topEven->below;

	


	return temp->node;

}

struct TreeNode* PopOdd() 
{
	struct StackNode* temp = topOdd;

	
	topOdd = topOdd->below;


	return temp->node;

}

struct TreeNode* newTreeNode(int data) 
{



	struct TreeNode* temp = (struct TreeNode*)malloc(sizeof(struct TreeNode));

	temp -> data = data;

	temp->right = NULL;
	temp->left  = NULL;

	return temp;

}

void PushEven(struct TreeNode* node) 
{
	struct StackNode* temp = (struct StackNode*)malloc(sizeof(struct StackNode));

	temp ->node = node;
	temp ->below = topEven;

	if(node!=NULL)     // If a NULL value has been entered, we've reached a leaf (THE END)
	topEven = temp;

	else topEven=NULL;


}

void PushOdd(struct TreeNode* node) 
{
	
	struct StackNode* temp = (struct StackNode*)malloc(sizeof(struct StackNode));

	temp ->node = node;
	temp ->below = topOdd;

	if(node!=NULL)	// If a NULL value has been entered, we've reached a leaf (THE END)
	topOdd = temp;

	else topOdd=NULL;

}



void printSpiral(struct TreeNode* root) 

{

	struct TreeNode* curr = NULL;

	PushEven(root);


	int level = 0;



	while(topEven!=NULL || topOdd!=NULL) 
	{
		//if(topEven!=NULL) {printf("TopEven is NOT NULL %d\n",topEven->node->data);}
		//if(topOdd!=NULL)  {printf("TopOdd is NOT NULL %d\n",topOdd->node->data);}

		while(level%2==0 && topEven!=NULL) 	//Even Level , push right element first and then left element 
						//and pop element from stack. Do this till empty stack ==> EvenStack
		{
			curr=PopEven();
			printf("%d ",curr->data);

			PushOdd(curr->right);
			PushOdd(curr->left);
			
			

			if(topEven==NULL) 
			{

				level++; 	//We've finished processing this even level. Move to the next level
				printf("\n");
			}

		}


		while(level%2!=0 && topOdd!=NULL)	//Odd Level , push left element first and then right element 
						//and pop element from stack. Do this till empty stack ==> OddStack
		{
			
			curr=PopOdd();
			printf("%d ",curr->data);

			PushEven(curr->left);
			PushEven(curr->right);
			
			

			if(topOdd==NULL) 
			{

				level++; 	//We've finished processing this odd level. Move to the next level
				printf("\n");
			}
			
		}


	}




}

int main() 
{
	topOdd  = NULL;
	topEven = NULL;



	root = newTreeNode(1);
	root->left = newTreeNode(2);
	root->right = newTreeNode(3);
	root->left->left = newTreeNode(4);
	root->left->right = newTreeNode(5);
	root->right->left = newTreeNode(6);
	root->right->right = newTreeNode(7);

	printSpiral(root);

	return 0;
}