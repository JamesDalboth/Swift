#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
} fs_in;

void main()
{
	float dist = 1.0f;
	color = vec4(dist);
}